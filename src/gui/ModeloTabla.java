/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Elliot
 */
public class ModeloTabla extends DefaultTableModel {

    String[] titulos;
    Object[][] datos;

    /**
     * Determina el modelo con el que se va a construir la tabla
     *
     * @param datos
     * @param titulos
     */
    public ModeloTabla(Object[][] datos, String[] titulos) {
        super();
        this.titulos = titulos;
        this.datos = datos;
        setDataVector(datos, titulos);
    }

    public ModeloTabla() {
        // TODO Auto-generated constructor stub
    }

    public boolean isCellEditable(int row, int column) {
        //Definimos si una celda puede ser o no editable
        if (column != Utilidades.PERFIL && column != Utilidades.EVENTO && column != Utilidades.NOTA1 && column != Utilidades.NOTA2 && column != Utilidades.NOTA3) {
            return false;
        } else {
            return true;
        }

    }
}
