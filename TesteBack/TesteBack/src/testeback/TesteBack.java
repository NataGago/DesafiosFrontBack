package testeback;
// Imports

import java.sql.SQLException;
import java.sql.Connection;

public class TesteBack {

    public static void main(String[] args) {
        Connection conn = null;
        TbCustomerAccount tbca;

        try {

            //obtem conexão com o Banco
            ConexaoBD bd = new ConexaoBD();
            conn = bd.conectar();

            // *Inserção do Primeiro Cliente*
            tbca = new TbCustomerAccount(1000, "Natã Gago", "12312312344", true, 600);
            tbca.insert(conn);

            // Segundo Cliente
            tbca = new TbCustomerAccount(1500, "Gabriel Silva", "43243243244", false, 540);
            tbca.insert(conn);

            // Terceiro Cliente
            tbca = new TbCustomerAccount(2000, "Rosana Almeida", "65465465477", true, 580);
            tbca.insert(conn);

            // Quarto Cliente
            tbca = new TbCustomerAccount(2300, "Cristiano Ronaldo", "78978978988", false, 645);
            tbca.insert(conn);

            // Quinto Cliente
            tbca = new TbCustomerAccount(2700, "Roberto Ferreira", "65378395266", true, 420);
            tbca.insert(conn);

            // Sexto Cliente
            tbca = new TbCustomerAccount(3000, "Carla Sanches", "7595759684", true, 630);
            tbca.insert(conn);

            // Lista Todos os Clientes até o momento
            tbca.select(conn);
            
            //Lista a Média dos clientes com o vl_total > 560 e o id_customer entre 1500 e 2700
            tbca.selectMedia(conn);
            
            // Lista os clientes com o vl_total > 560 e o id_customer entre 1500 e 2700
            tbca.selectCustomersMedia(conn);
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }
}
