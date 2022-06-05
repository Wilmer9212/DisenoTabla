/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Elliot
 */
public class TestTabla extends javax.swing.JFrame {

    ModeloTabla modelo;
    private int filasTabla;
    private int columnasTabla;

    ArrayList<PersonaVo> listaPersonas;

    public TestTabla() {
        initComponents();
        tablaPersonas.setOpaque(false);
        construirTabla();
    }

    private void construirTabla() {
        listaPersonas = consultarListaPersonas();
        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("Documento");
        titulosList.add("Nombre");
        titulosList.add("Direccion");
        titulosList.add("Telefono");
        titulosList.add("Profesion");
        titulosList.add("Edad");
        titulosList.add("Nota1");
        titulosList.add("Nota2");
        titulosList.add("Nota3");
        titulosList.add("Promedio");
        titulosList.add(" ");
        titulosList.add(" ");

        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = titulosList.get(i);
        }
        /*obtenemos los datos de la lista y los guardamos en la matriz
		 * que luego se manda a construir la tabla
         */
        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTabla(titulos, data);

    }

    private ArrayList<PersonaVo> consultarListaPersonas() {
        ArrayList<PersonaVo> lista = new ArrayList<>();

        lista.add(new PersonaVo("1234", "Cristian David Henao", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 23, 2.5, 4.3, 3.0, (2.5 + 4.3 + 3) / 33));
        lista.add(new PersonaVo("3455", "Juan Camilo Perez", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 0, 0, 0, 0, 0));
        lista.add(new PersonaVo("3214", "Marlon Guapacha", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 0, 0, 0, 0, 0));
        lista.add(new PersonaVo("7886", "Marina Marin", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 0, 0, 0, 0, 0));
        lista.add(new PersonaVo("4331", "Juliana Henao", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 0, 0, 0, 0, 0));
        lista.add(new PersonaVo("98675", "David Henao", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 0, 0, 0, 0, 0));
        lista.add(new PersonaVo("1221", "Cristian mendez Henao", "Calle 2# 23-09 Armenia", "7564323", "Ingeniero", 0, 0, 0, 0, 0));

        return lista;
    }

    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new ModeloTabla(data, titulos);
        //se asigna el modelo a la tabla
        tablaPersonas.setModel(modelo);

        filasTabla = tablaPersonas.getRowCount();
        columnasTabla = tablaPersonas.getColumnCount();

        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        tablaPersonas.getColumnModel().getColumn(Utilidades.EDAD).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA1).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA2).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA3).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.PROMEDIO).setCellRenderer(new GestionCeldas("numerico"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.PERFIL).setCellRenderer(new GestionCeldas("icono"));
        tablaPersonas.getColumnModel().getColumn(Utilidades.EVENTO).setCellRenderer(new GestionCeldas("icono"));

        //se recorre y asigna el resto de celdas que serian las que almacenen datos de tipo texto
        for (int i = 0; i < titulos.length - 7; i++) {//se resta 7 porque las ultimas 7 columnas se definen arriba
            System.out.println(i);
            tablaPersonas.getColumnModel().getColumn(i).setCellRenderer(new GestionCeldas("texto"));
        }

        tablaPersonas.getTableHeader().setReorderingAllowed(false);
        tablaPersonas.setRowHeight(25);//tamaño de las celdas
        tablaPersonas.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tamaño de largo para cada columna y su contenido
        tablaPersonas.getColumnModel().getColumn(Utilidades.DOCUMENTO).setPreferredWidth(130);//documento
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOMBRE).setPreferredWidth(380);//nombre
        tablaPersonas.getColumnModel().getColumn(Utilidades.DIRECCION).setPreferredWidth(350);//direccion
        tablaPersonas.getColumnModel().getColumn(Utilidades.TELEFONO).setPreferredWidth(130);//telefono
        tablaPersonas.getColumnModel().getColumn(Utilidades.PROFESION).setPreferredWidth(280);//profesion
        tablaPersonas.getColumnModel().getColumn(Utilidades.EDAD).setPreferredWidth(80);//edad
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA1).setPreferredWidth(100);//nota1
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA2).setPreferredWidth(100);//nota2
        tablaPersonas.getColumnModel().getColumn(Utilidades.NOTA3).setPreferredWidth(100);//nota3
        tablaPersonas.getColumnModel().getColumn(Utilidades.PROMEDIO).setPreferredWidth(130);//promedio
        tablaPersonas.getColumnModel().getColumn(Utilidades.PERFIL).setPreferredWidth(30);//accion perfil
        tablaPersonas.getColumnModel().getColumn(Utilidades.EVENTO).setPreferredWidth(30);//accion evento

        //personaliza el encabezado
        JTableHeader jtableHeader = tablaPersonas.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        tablaPersonas.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        //scrollPaneTabla.setViewportView(tablaPersonas);
    }

    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {

        /*se crea la matriz donde las filas son dinamicas pues corresponde
		 * a todos los usuarios, mientras que las columnas son estaticas
		 * correspondiendo a las columnas definidas por defecto
         */
        String informacion[][] = new String[listaPersonas.size()][titulosList.size()];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][Utilidades.DOCUMENTO] = listaPersonas.get(x).getDocumento() + "";
            informacion[x][Utilidades.NOMBRE] = listaPersonas.get(x).getNombre() + "";
            informacion[x][Utilidades.DIRECCION] = listaPersonas.get(x).getDireccion() + "";
            informacion[x][Utilidades.TELEFONO] = listaPersonas.get(x).getTelefono() + "";
            informacion[x][Utilidades.PROFESION] = listaPersonas.get(x).getProfesion() + "";
            informacion[x][Utilidades.EDAD] = listaPersonas.get(x).getEdad() + "";
            informacion[x][Utilidades.NOTA1] = listaPersonas.get(x).getNota1() + "";
            informacion[x][Utilidades.NOTA2] = listaPersonas.get(x).getNota2() + "";
            informacion[x][Utilidades.NOTA3] = listaPersonas.get(x).getNota3() + "";
            informacion[x][Utilidades.PROMEDIO] = listaPersonas.get(x).getPromedio() + "";
            //se asignan las plabras clave para que en la clase GestionCeldas se use para asignar el icono correspondiente
            informacion[x][Utilidades.PERFIL] = "PERFIL";
            informacion[x][Utilidades.EVENTO] = "EVENTO";
        }

        return informacion;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPersonas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPersonas;
    // End of variables declaration//GEN-END:variables
}
