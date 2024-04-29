
package exp2_s08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EXP2_S08 {
  static int opcionMenu = 0;
  static int cantidadBoletos = 0;
  static int esTerceraEdad = 0;
  static int esEstudiante = 0;

  static int totalAsientos = 100;

  static long[] arrayIdVentas = new long[100];

  static int precioEntrada;
  static String[] arrayUbicacionEntrada = new String[100];
  static List<Integer> listaPrecioEntrada = new ArrayList<>();

  static double valorDescuento;
  static List<Double> listaValorDescuento = new ArrayList<>();
  static List<String> listaTipoDescuento = new ArrayList<>();

  static String tipoEntrada;
  static String tipoDescuento;

  static Scanner entradaUsuario = new Scanner(System.in);

  static void calcularDescuento(){
    System.out.println("+++++ Verifica si aplican descuentos +++++");
    do{
      System.out.println("¿El cliente es de tercera edad? [1]Sí [2]No");
      esTerceraEdad = entradaUsuario.nextInt();
      if(esTerceraEdad != 1 && esTerceraEdad != 2){
        System.out.println("ERROR: Introduzca una opción válida \n");
      }
    } while (esTerceraEdad != 1 && esTerceraEdad != 2);

    if(esTerceraEdad == 1){
      valorDescuento = precioEntrada * 0.15;
      tipoDescuento = "15% Tercera edad.";
    }
    else{
      do {
        System.out.println("¿El cliente es estudiante? [1]Sí [2]No");
        esEstudiante = entradaUsuario.nextInt();
        if(esEstudiante != 1 && esEstudiante != 2){
          System.out.println("ERROR: Introduzca una opción válida \n");
        }
      } while(esEstudiante != 1 && esEstudiante != 2);

      if(esEstudiante == 1){
        valorDescuento = precioEntrada * 0.10;
        tipoDescuento = "10% Estudiante.";
      }
      else {
        valorDescuento = 0;
        tipoDescuento = "No aplica descuento";
      }
    }

    //System.out.println(tipoDescuento + " - Precio final: $" + (precioEntrada - valorDescuento) + "\n");
  }

  static void armarArrays(String ubicacionBoleto, int precioBoleto, double precioDescuentoBoleto){
    //Array ID Venta
    arrayIdVentas[cantidadBoletos] = System.currentTimeMillis();
    //Lista Precios
    listaPrecioEntrada.add(cantidadBoletos, precioBoleto);
    //Lista descuentos
    listaTipoDescuento.add(cantidadBoletos, tipoDescuento);
    listaValorDescuento.add(cantidadBoletos, precioDescuentoBoleto);
    arrayUbicacionEntrada[cantidadBoletos] = ubicacionBoleto;

  }

  static void elegirUbicacion(int ubicacion){

    switch (ubicacion) {
      case 1:
        precioEntrada = 25000;
        tipoEntrada = "VIP";
        break;
      case 2:
        precioEntrada = 19000;
        tipoEntrada = "Platea Baja";

        break;
      case 3:
        precioEntrada = 11000;
        tipoEntrada = "Platea Alta";
        break;
      case 4:
        precioEntrada = 7500;
        tipoEntrada = "Palco";
        break;
    }

    System.out.println("Boleto #" + (cantidadBoletos + 1) );
    System.out.println("Entrada: " + tipoEntrada + " - Precio: $" + precioEntrada);

    calcularDescuento();

    armarArrays(tipoEntrada, precioEntrada, valorDescuento);
    
    System.out.println("Boleto #" + (cantidadBoletos + 1) );
    System.out.println("Entrada: " + tipoEntrada + " - Precio: $" + precioEntrada);
    System.out.println(tipoDescuento + " - Precio final: $" + (precioEntrada - valorDescuento) + "\n");
  }

  static void mostrarResumen(){
    System.out.println("+++++++++ UBICACIONES SELECCIONADAS +++++++++");
    for (int i = 0; i < cantidadBoletos ; i++) {
      System.out.println("Resumen del boleto #" + (i + 1));
      System.out.println("------------------------");
      System.out.println("ID Boleto: " + arrayIdVentas[i]);
      System.out.println("Ubicación: " + arrayUbicacionEntrada[i]);
      System.out.println("Precio Boleto: $" + listaPrecioEntrada.get(i));
      System.out.println("Descuento: " + listaTipoDescuento.get(i));
      System.out.println("Valor Descuento $" + listaValorDescuento.get(i) + "\n");
    }
  }

  static void editarUbicacion(){
    System.out.println("¿Qué ubicación quieres editar?");
    int opcionUbicacionEditar;
    do {
      for (int i = 0; i < arrayUbicacionEntrada.length ; i++) {
        if(arrayUbicacionEntrada[i] != null){
          System.out.println("[" + (i + 1) + "]" + arrayUbicacionEntrada[i] + " - ID: " + arrayIdVentas[i]);
        }
      }
      opcionUbicacionEditar = entradaUsuario.nextInt();
      if (opcionUbicacionEditar < 1 || opcionUbicacionEditar > cantidadBoletos) {
        System.out.println("ERROR: Introduzca una opción válida \n");
      }
    } while(opcionUbicacionEditar < 1 || opcionUbicacionEditar > cantidadBoletos);

    int nuevaUbicacion;
    do {
      System.out.println("¿Cuál es la nueva ubicación para este boleto?");
      System.out.println(" [1]VIP [2]Platea Baja [3]Platea Alta [4]Palco");
      nuevaUbicacion = entradaUsuario.nextInt();

      if (nuevaUbicacion < 1 || nuevaUbicacion > 4 ) {
        System.out.println("ERROR: Introduzca una opción válida \n");
      }
    } while (nuevaUbicacion < 1 || nuevaUbicacion > 4);

    String nuevaUbicacionTipo = "";
    int precioEdicion = 0;

    switch (nuevaUbicacion){
      case 1:
        nuevaUbicacionTipo = "VIP";
        precioEdicion = 25000;
        break;
      case 2:
        nuevaUbicacionTipo = "Platea Baja";
        precioEdicion = 19000;
        break;
      case 3:
        nuevaUbicacionTipo = "Platea Alta";
        precioEdicion = 11000;
        break;
      case 4:
        nuevaUbicacionTipo = "Palco";
        precioEdicion = 7500;
        break;
    }
    arrayUbicacionEntrada[opcionUbicacionEditar - 1] = nuevaUbicacionTipo;
    listaPrecioEntrada.set(opcionUbicacionEditar - 1, precioEdicion);

    String tipoDescuentoEdicion = listaTipoDescuento.get(opcionUbicacionEditar - 1);
    if(tipoDescuentoEdicion.equals("15% Tercera edad.")){
      valorDescuento = precioEdicion * 0.15;
    }
    if(tipoDescuentoEdicion.equals("10% Estudiante.")){
      valorDescuento = precioEdicion * 0.10;
    }

    listaValorDescuento.set(opcionUbicacionEditar - 1, valorDescuento);

    System.out.println("+++ La ubicación ha sido actualizada con éxito +++ \n");
  }

  static void eliminarBoleto(){
    System.out.println("¿Qué ubicación quieres eliminar?");
    int boletoParaEliminar;
    do {
      for (int i = 0; i < arrayUbicacionEntrada.length ; i++) {
        if(arrayUbicacionEntrada[i] != null){
          System.out.println("[" + (i+1) + "]" + arrayUbicacionEntrada[i] + " - ID: " + arrayIdVentas[i]);
        }
      }
      boletoParaEliminar = entradaUsuario.nextInt();
      if (boletoParaEliminar < 1 || boletoParaEliminar > cantidadBoletos) {
        System.out.println("ERROR: Introduzca una opción válida \n");
      }

    } while(boletoParaEliminar < 1 || boletoParaEliminar > cantidadBoletos);

    //Simular el comportamiento de un Lista, moviendo los elementos restantes del array hacia la izquierda.
    for (int i = boletoParaEliminar - 1; i < (totalAsientos - 1) ; i++) {
      arrayIdVentas[i] = arrayIdVentas[i + 1];
      arrayUbicacionEntrada[i] = arrayUbicacionEntrada[i + 1];
    }
    listaPrecioEntrada.remove(boletoParaEliminar - 1);
    listaTipoDescuento.remove(boletoParaEliminar - 1);
    listaValorDescuento.remove(boletoParaEliminar - 1);

    cantidadBoletos --;
    totalAsientos ++;

    System.out.println("++Lista actualizada de ubicaciones++");
    mostrarResumen();
  }
 
  public static void main(String[] args) {
    do {
      System.out.println("+++++++++++++++++++++++ SISTEMA DE GESTIÓN DE VENTA DE ENTRADAS TEATRO MORO +++++++++++++++++++++++");
      System.out.println("            [1]Ver Promociones [2]Elegir Ubicación [3]Ver ubicaciones seleccionadas");
      System.out.println("      [4]Editar ubicación [5]Eliminar ubicación [6]Finalizar compra y/o salir del sistema");
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      opcionMenu = entradaUsuario.nextInt();

      //Validar que el usuario ingrese la opción correcta
      if (opcionMenu < 1 || opcionMenu > 6 ) {
        System.out.println("ERROR: Introduzca una opción válida \n");
      }

      switch (opcionMenu) {
        //MOSTRAR DESCUENTOS
        case 1:
          System.out.println("+++++++++ DESCUENTOS +++++++++");
          System.out.println(" 10% de descuento Estudiante");
          System.out.println(" 15% de descuento 3ra edad \n");
          break;

        //ELECCION UBICACIÓN Y VERIFICAR DESCUENTO
        case 2:
          int opcionUbicacion;
          if(totalAsientos > 0 && totalAsientos <= 100){
            do {

              System.out.println("+++++++++++++++++ UBICACIONES +++++++++++++++++");
              System.out.println("   -- Existen " + totalAsientos + " asientos disponibles.--  ");
              System.out.println(" [1]VIP [2]Platea Baja [3]Platea Alta [4]Palco");
              opcionUbicacion = entradaUsuario.nextInt();

              if(opcionUbicacion < 1 || opcionUbicacion > 4){
                System.out.println("ERROR: Introduzca una opción válida \n");
              }
            } while (opcionUbicacion < 1 || opcionUbicacion > 4);

            elegirUbicacion(opcionUbicacion);
            cantidadBoletos++;
            totalAsientos --;
          } else {
            System.out.println("No quedan asientos disponibles. \n");
          }

          break;

        //MOSTRAR RESUMEN DE UBICACIONES
        case 3:
          if (cantidadBoletos == 0){
            System.out.println("No hay seleccionada ninguna ubicación");
          } else{
            mostrarResumen();
          }
          break;

        //EDITAR UBICACION
        case 4:
          if (cantidadBoletos == 0){
            System.out.println("No hay ubicaciones seleccionadas para editar \n");
          } else{
            System.out.println("+++++++++ EDITAR UBICACIÓN +++++++++");
            editarUbicacion();
          }
          break;

        //ELIMINAR BOLETO
        case 5:
          if (cantidadBoletos == 0){
            System.out.println("No hay ubicaciones seleccionadas para eliminar \n");
          } else{
            System.out.println("+++++++++ ELIMINAR UBICACIÓN +++++++++");
            eliminarBoleto();
          }
          break;
      }
    } while(opcionMenu != 6);
    
    if(cantidadBoletos >= 1){
      System.out.println("+++++++++ BOLETA TEATRO MORO +++++++++");
      
      mostrarResumen();
      
      int subtotal = 0;
      double totalDescuentos = 0;

      for (int i = 0; i < listaPrecioEntrada.size(); i++) {
        subtotal += listaPrecioEntrada.get(i);
      }
      for (int i = 0; i < listaValorDescuento.size(); i++) {
        totalDescuentos += listaValorDescuento.get(i);
      }

      System.out.println("++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("TOTAL ENTRADAS: " + cantidadBoletos);
      System.out.println("SUBTOTAL: $" + subtotal);
      System.out.println("DESCUENTOS: $" + totalDescuentos);
      System.out.println("++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("TOTAL A PAGAR: $" + (subtotal - totalDescuentos));
      System.out.println("++++++++++++++++++++++++++++++++++++++++++ \n");
      System.out.println("++++++++++++ Gracias por utilizar el Sistema de Gestión entradas ++++++++++++++++++");
      System.out.println("++++++++++++               Disfruta la función                   ++++++++++++++++++");
    } else {
      System.out.println("++++++++++++           Has salido del sistema          ++++++++++++++++++");
    }
    
    
  }
  
}
