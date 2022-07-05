package Test;

import controller.HospedeController;

public class Tconnection {
    public static void main(String[] args) {
        buscar();
    }

    public static void buscar() {
        String name = "Barry";
        HospedeController hc = new HospedeController();
        hc.buscar(name).forEach(nome -> {
            System.out.println(nome.getNome());
            System.out.println(nome.getIdHospede());

        });

    }
}
