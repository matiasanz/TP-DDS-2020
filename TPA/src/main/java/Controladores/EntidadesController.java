package Controladores;

import Categoria.Categoria;
import Direccion.Direccion;
import Direccion.Pais;
import Entidad.*;
import Repositorios.RepositorioDeCategorias;
import Repositorios.RepositorioDeEntidades;
import Repositorios.RepositorioDeLocaciones.RepositorioDeLocacionesMeli;
import org.apache.commons.lang3.EnumUtils;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntidadesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

    public ModelAndView getEntidadesMenu(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "entidades_base.html.hbs");
    }

    public ModelAndView getCategoriasAElegir(Request request, Response response) {
        String categoriaBuscada = request.queryParams("filtro");
        Map<String, Object> modelo = new HashMap<>();

        List<Categoria> categorias = categoriaBuscada != null?
                RepositorioDeCategorias.instancia.getCategoriasByName(categoriaBuscada) :
                RepositorioDeCategorias.instancia.findAll();

        modelo.put("categorias", categorias);

        return new ModelAndView(modelo,"entidades_por_categoria.html.hbs");
    }

    public ModelAndView getCreadorEntidad(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "entidades_nueva.html.hbs");
    }

    public ModelAndView getCreadorEntidadBase(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("categorias", RepositorioDeCategorias.instancia.findAll());

        return new ModelAndView(modelo, "entidades_nueva_base.html.hbs");
    }

    public ModelAndView getCreadorEmpresa(Request request, Response response) {
        return getCreadorDeJuridicas(request, response,"entidades_nueva_empresa.html.hbs");
    }

    public ModelAndView getCreadorOrgSectorSocial(Request request, Response response) {
        return getCreadorDeJuridicas(request, response,"entidades_nueva_org-social.html.hbs");
    }

    private ModelAndView getCreadorDeJuridicas(Request request, Response response, String html){
        Map<String, Object> modelo = new HashMap<>();

        List<Entidad> entidadesBase = RepositorioDeEntidades.instancia.getEntidadesBase();

        if (!entidadesBase.isEmpty()) {
            modelo.put("entidadesBaseNotEmpty", Boolean.TRUE);
            modelo.put("entidadesBase", entidadesBase);
        }

        modelo.put("categorias", RepositorioDeCategorias.instancia.findAll());
        modelo.put("paises", EnumUtils.getEnumList(Pais.class).stream().map(Enum::name).collect(Collectors.toList()));

        return new ModelAndView(modelo, html);
    }

    public ModelAndView crearEntidad(Request request, Response response) {
        switch (request.queryParams("tipoEntidad").charAt(0)){
            case 'B':  return crearEntidadBase(request, response);
            case 'E':  return crearEmpresa(request, response);
            case 'S':  return crearOrganizacionSectorSocial(request, response);
            default: return returnError400(response);
        }
    }

    private ModelAndView crearEntidadBase(Request request, Response response){
        try{
            String nombre = request.queryParams("nombreFicticio");
            String descripcion = request.queryParams("descripcion");
            List<Categoria> categorias = getCategoriasFromRequest(request);
            EntidadBase entidad = new EntidadBase(nombre, descripcion);
            entidad.setCategorias(categorias);

            withTransaction(() -> RepositorioDeEntidades.instancia.save(entidad));
            return retornarMenuEntidadesExito(response);

        } catch(Exception e){
            return retornarMenuEntidadesFallo(e);
        }
    }

    private ModelAndView crearEmpresa(Request request, Response response){
        try{
            String nombreFicticio = request.queryParams("nombreFicticio");
            List<Categoria> categorias = getCategoriasFromRequest(request);
            String razonSocial = request.queryParams("razonSocial");
            String cuit = request.queryParams("cuit");
            Integer codigoIgj = Integer.parseInt(request.queryParams("igj"));
            Direccion direccion = crearDireccion(request);
            List<EntidadBase> entidadesRelacionadas = getEntidadesBaseRelacionadasFromRequest(request);
            Clasificacion clasificacion = Clasificacion.values()[Integer.parseInt(request.queryParams("clasificacion"))];

            Empresa empresa = new Empresa(razonSocial, nombreFicticio, cuit, direccion, codigoIgj, entidadesRelacionadas, clasificacion);
            empresa.setCategorias(categorias);

            withTransaction(() -> RepositorioDeEntidades.instancia.save(empresa));
            return retornarMenuEntidadesExito(response);

        } catch(Exception e){
            return retornarMenuEntidadesFallo(e);
        }
    }


    private ModelAndView crearOrganizacionSectorSocial(Request request, Response response){
        try{
            String nombreFicticio = request.queryParams("nombreFicticio");
            List<Categoria> categorias = getCategoriasFromRequest(request);
            String razonSocial = request.queryParams("razonSocial");
            String cuit = request.queryParams("cuit");
            Integer codigoIgj = Integer.parseInt(request.queryParams("igj"));
            Direccion direccion = crearDireccion(request);
            List<EntidadBase> entidadesRelacionadas = getEntidadesBaseRelacionadasFromRequest(request);

            OrganizacionSectorSocial org = new OrganizacionSectorSocial(razonSocial, nombreFicticio, cuit, direccion, codigoIgj, entidadesRelacionadas);
            org.setCategorias(categorias);

            withTransaction(() -> RepositorioDeEntidades.instancia.save(org));
            return retornarMenuEntidadesExito(response);

        } catch(Exception e){
            return retornarMenuEntidadesFallo(e);
        }
    }

    private Direccion crearDireccion(Request request){
        String calle = request.queryParams("calle");
        Integer altura = Integer.parseInt(request.queryParams("altura"));
        Integer piso = Integer.parseInt(request.queryParams("altura"));
        String codigoPostal = request.queryParams("codigoPostal");
        Pais pais = Pais.valueOf(request.queryParams("pais"));
        return new Direccion(new RepositorioDeLocacionesMeli(), calle, altura, piso, codigoPostal, pais);
    }

    private List<Categoria> getCategoriasFromRequest (Request request){
        return request.queryParams().stream().filter(s -> s.startsWith("categoriasId")).
                map(request::queryParams).map(Long::parseLong).
                map(l -> RepositorioDeCategorias.instancia.findById(l)).collect(Collectors.toList());
    }

    private List<EntidadBase> getEntidadesBaseRelacionadasFromRequest (Request request){
        return request.queryParams().stream().filter(s -> s.startsWith("entidadesId")).
                map(request::queryParams).map(Long::parseLong).
                map(l -> RepositorioDeEntidades.instancia.findBaseById(l)).collect(Collectors.toList());
    }

    private ModelAndView retornarMenuEntidadesExito(Response response){
        response.status(201);

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("mensajeAccion", "La entidad fue creada con éxito.");
        modelo.put("categorias", RepositorioDeCategorias.instancia.findAll());
        return new ModelAndView(modelo,"entidades_nueva.html.hbs");
    }

    private ModelAndView retornarMenuEntidadesFallo(Exception e){
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("mensajeAccion", "Ocurrió un error en la creación de la entidad: " + e.getMessage());
        return new ModelAndView(modelo,"entidades_nueva.html.hbs");
    }

    public ModelAndView getEntidadesPorCategoria(Request request, Response response){

        try{
            Map<String, Object> modelo = new HashMap<>();

            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.params(":id")));

            if (categoria == null){
                return returnError404(response);
            }

            List<Entidad> entidades = RepositorioDeEntidades.instancia.listarPorCategoria(Long.parseLong(request.params(":id")));

            response.status(200);
            modelo.put("categoria", categoria);
            modelo.put("entidades", entidades);

            return new ModelAndView(modelo, "entidades_por_categoria_elegida.html.hbs");

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    public ModelAndView getEntidadesAAsociar(Request request, Response response) {

        String nombreEntidad = request.queryParams("filtro");
        Map<String, Object> modelo = new HashMap<>();

        List<Entidad> entidades = nombreEntidad != null ?
                RepositorioDeEntidades.instancia.getEntidadesByName(nombreEntidad) :
                RepositorioDeEntidades.instancia.getEntidades();

        modelo.put("entidades", entidades);

        return new ModelAndView(modelo, "entidades_a_asociar.html.hbs");
    }

    public ModelAndView getEntidadYSusCategorias(Request request, Response response) {

        try{
            Map<String, Object> modelo = new HashMap<>();

            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));

            if (entidad == null){
                return returnError404(response);
            }

            return getModelConCategoriasAsociadasYSinAsociar(modelo, entidad);

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    public ModelAndView agregarCategoriaAEntidad(Request request, Response response) {

        try{
            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.queryParams("idCategoria")));

            if (entidad == null || categoria == null){
                return returnError404(response);
            }

            Map<String, Object> modelo = new HashMap<>();

            if (!entidad.getCategorias().contains(categoria)){
                withTransaction(() -> {
                    entidad.agregarCategoria(categoria);
                    RepositorioDeEntidades.instancia.save(entidad);
                } );
                modelo.put("mensajeAccion", "La categoría " + categoria.getNombre() + " fue asociada correctamente.");
            }

            return getModelConCategoriasAsociadasYSinAsociar(modelo, entidad);

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    public ModelAndView eliminarCategoriaDeEntidad(Request request, Response response) {

        try{
            Entidad entidad = RepositorioDeEntidades.instancia.findById(Long.parseLong(request.params(":id")));
            Categoria categoria = RepositorioDeCategorias.instancia.findById(Long.parseLong(request.queryParams("idCategoria")));

            if (entidad == null || categoria == null){
                return returnError404(response);
            }

            withTransaction(() -> {
                entidad.eliminarCategoria(categoria);
                RepositorioDeEntidades.instancia.save(entidad);
            } );

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("mensajeAccion", "La categoría " + categoria.getNombre() + " fue desasociada correctamente.");

            return getModelConCategoriasAsociadasYSinAsociar(modelo, entidad);

        } catch(NumberFormatException e) {
            return returnError400(response);
        }
    }

    private ModelAndView returnError400(Response response) {
        return returnError(response, 400, "Error 400 - Tipo de ID incorrecto");
    }

    private ModelAndView returnError404(Response response) {
        return returnError(response, 404, "Error 404 - Entidad y/o categoría no encontrada");
    }

    private ModelAndView returnError(Response response, int status, String mensaje){
        Map<String, Object> modelo = new HashMap<>();
        response.status(status);
        modelo.put("mensajeError", mensaje);
        return new ModelAndView(modelo, "errores.html.hbs");
    }

    private ModelAndView getModelConCategoriasAsociadasYSinAsociar(Map<String, Object> modelo, Entidad entidad) {
        List<Categoria> categoriasAsociadas = entidad.getCategorias();
        List<Categoria> categoriasSinAsociar = RepositorioDeCategorias.instancia.findAll().
                stream().filter(c -> !categoriasAsociadas.contains(c)).collect(Collectors.toList());

        modelo.put("categoriasAsociadas", categoriasAsociadas);
        modelo.put("categoriasSinAsociar", categoriasSinAsociar);
        modelo.put("idEntidad", entidad.getId());
        modelo.put("entidad", entidad);
        return new ModelAndView(modelo, "entidades_a_asociar_elegida.html.hbs");
    }
}
