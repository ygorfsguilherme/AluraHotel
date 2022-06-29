package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.ReservasController;
import model.ReservasModelo;

public class TesteConexao {
    public static void main(String[] args) throws ParseException {
        ReservasController reservasController = new ReservasController();
        reservasController.deletar(2);
    }

}
