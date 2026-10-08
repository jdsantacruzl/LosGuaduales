import java.util.Scanner;

public class LosGuaduales {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        double[] consumos = new double[6];

        int opcion;

        do {
            mostrarMenu();
            opcion = entrada.nextInt();

            switch (opcion) {

                case 1:
                    registrarConsumo(consumos);
                    break;

                case 2:
                    consultarFactura(consumos);
                    break;

                case 3:
                    generarReporte(consumos);
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }


    public static void mostrarMenu() {

        System.out.println("\n===== LOS GUADUALES - CONTROL DE AGUA =====");
        System.out.println("1. Registrar consumo de un apartamento");
        System.out.println("2. Consultar factura de un apartamento");
        System.out.println("3. Ver reporte general del conjunto");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }


    public static void registrarConsumo(double[] consumos) {

        int apartamento;
        double consumo;

        System.out.print("Ingrese el número de apartamento (1-6): ");
        apartamento = entrada.nextInt();

        while (apartamento < 1 || apartamento > 6) {
            System.out.println("Número de apartamento inválido.");
            System.out.print("Ingrese el número de apartamento (1-6): ");
            apartamento = entrada.nextInt();
        }

        System.out.print("Ingrese el consumo en m³: ");
        consumo = entrada.nextDouble();

        while (consumo <= 0) {
            System.out.println("El consumo debe ser mayor que 0.");
            System.out.print("Ingrese el consumo en m³: ");
            consumo = entrada.nextDouble();
        }

        consumos[apartamento - 1] = consumo;

        System.out.println("Consumo registrado correctamente.");
    }


    public static void consultarFactura(double[] consumos) {

        int apartamento;

        System.out.print("Ingrese el número de apartamento (1-6): ");
        apartamento = entrada.nextInt();

        while (apartamento < 1 || apartamento > 6) {
            System.out.println("Número de apartamento inválido.");
            System.out.print("Ingrese el número de apartamento (1-6): ");
            apartamento = entrada.nextInt();
        }

        double consumo = consumos[apartamento - 1];

        if (consumo == 0) {
            System.out.println("El apartamento " + apartamento +
                    " no tiene consumo registrado.");
        } else {
            imprimirFactura(apartamento, consumo);
        }
    }


    public static void imprimirFactura(int apartamento, double consumo) {

        double cargoFijo = 12000;
        double tarifa;
        double valorConsumo;
        double subtotal;
        double recargo = 0;
        double total;

        if (consumo <= 10) {
            tarifa = 3000;
        } else if (consumo <= 20) {
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }

        valorConsumo = consumo * tarifa;

        subtotal = cargoFijo + valorConsumo;

        if (consumo > 25) {
            recargo = subtotal * 0.10;
        }

        total = subtotal + recargo;

        System.out.println("\n========== FACTURA ==========");
        System.out.println("Apartamento: " + apartamento);
        System.out.println("Consumo: " + consumo + " m³");
        System.out.println("Tarifa aplicada: $" + tarifa);
        System.out.println("Valor del consumo: $" + valorConsumo);
        System.out.println("Cargo fijo: $" + cargoFijo);
        System.out.println("Recargo: $" + recargo);

        if (consumo > 25) {
            System.out.println("ALERTA: consumo excesivo");
        }

        System.out.println("TOTAL A PAGAR: $" + total);
        System.out.println("=============================");
    }


    public static void generarReporte(double[] consumos) {

        double consumoTotal = 0;
        double consumoMayor = 0;

        int apartamentoMayor = 0;
        int apartamentosRegistrados = 0;
        int apartamentosSinRegistro = 0;
        int consumosExcesivos = 0;

        for (int i = 0; i < consumos.length; i++) {

            if (consumos[i] > 0) {

                consumoTotal += consumos[i];
                apartamentosRegistrados++;

                if (consumos[i] > consumoMayor) {
                    consumoMayor = consumos[i];
                    apartamentoMayor = i + 1;
                }

                if (consumos[i] > 25) {
                    consumosExcesivos++;
                }

            } else {
                apartamentosSinRegistro++;
            }
        }

        if (apartamentosRegistrados == 0) {

            System.out.println("Aún no hay consumos registrados.");

        } else {

            double promedio = consumoTotal / apartamentosRegistrados;

            System.out.println("\n====== REPORTE GENERAL ======");
            System.out.println("Consumo total del conjunto: "
                    + consumoTotal + " m³");

            System.out.println("Consumo promedio: "
                    + promedio + " m³");

            System.out.println("Apartamento con mayor consumo: "
                    + apartamentoMayor);

            System.out.println("Mayor consumo: "
                    + consumoMayor + " m³");

            System.out.println("Apartamentos sin registro: "
                    + apartamentosSinRegistro);

            System.out.println("Apartamentos con consumo excesivo: "
                    + consumosExcesivos);

            System.out.println("=============================");
        }
    }
}