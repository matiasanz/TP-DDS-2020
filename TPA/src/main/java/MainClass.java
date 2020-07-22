import java.util.List;

import Compra.Compra;
import Compra.Estado;
import Organizacion.Organizacion;
import Presupuesto.Presupuesto;
import TareasProgramadas.ReporteMensualDeGastos;
import Usuario.Usuario;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class MainClass {

    public static void main(String[] args) throws SchedulerException {

        configurarTareasProgramadas();
        ejecutarValidacionDeEgresos();
    }

    private static void ejecutarValidacionDeEgresos() {
        Organizacion unaOrganizacion = Fabrica.organizacionStub();

//      GENERAR COMPRAS
        Compra compraQueAprueba = Fabrica.compraConNPresupuestosMinimos(1);
        Compra compraQueDesaprueba = Fabrica.compraConNPresupuestosMinimos(76);
        Compra compraNoValidable = Fabrica.compraEnEstado(Estado.NUEVA);

//  	unaOrganizacion.agregarCompra(compraQueAprueba);
//  	unaOrganizacion.agregarCompra(compraQueDesaprueba);
//  	unaOrganizacion.agregarCompra(compraNoValidable);

//        AGREGAR PRESUPUESTOS
        Presupuesto unPresupuesto = Fabrica.presupuestoPara(compraQueAprueba);
        compraQueAprueba.generarPresupuesto(unPresupuesto);

//        DEJAR COMPRAS CON PRESUPUESTOS SUFICIENTES Y OTRAS NO
//    	unaOrganizacion.validarPresupuestos();
        //Duda: La idea no es que despues tire la excepcion?
//
//        ELEGIR PRESUPUESTOS
        compraQueAprueba.setPresupuestoElegido(unPresupuesto);
        compraQueDesaprueba.setPresupuestoElegido(unPresupuesto);

//        PEDIRLE A USUARIO USER Y CONTRASEnia y VALIDAR EL LOGEO
//        Usuario miUsuario = unaOrganizacion.ingresarUsuario();

//        FILTER DE COMPRAS A LAS QUE MI USUARIO PUEDE VALIDAR
//        List<Compra> comprasQuePuedoValidar = unaOrganizacion.comprasQuePuedeValidar(miUsuario);

//        IMPRIMIR POR PANTALLA QUE SE COMENZARa A VALIDAR COMPRAS Y CUALES
        System.out.println("\n***************************** COMPRAS A VALIDAR *****************************\n");

        System.out.println("\n***************************** VALIDACIONES SOBRE LA MARCHA *****************************\n");
//        FALTA DEFINIR EL TOSTRING EN COMPRA
//        comprasQuePuedoValidar.stream().forEach(compra-> compra.validar());

        System.out.println("\n***************************** COMPRAS APROBADAS *****************************\n");


    }

    private static void configurarTareasProgramadas() throws SchedulerException {

        configurarReporteMensualDeGastos();
    }

    private static void configurarReporteMensualDeGastos() throws SchedulerException {

        JobDetail job = JobBuilder.newJob(ReporteMensualDeGastos.class).withIdentity("reporteDeGastos", "grupo1").build();


        Trigger triggerReporteMensualDeGastos = TriggerBuilder.newTrigger()
                .withIdentity("reporteDeGastos", "grupo1")
                // Ultimo día del mes a las 23:59 PM
                //.withSchedule(cronSchedule("0 59 23 L * ?"))
                // Cada 30 segundos
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, triggerReporteMensualDeGastos);

    }
}