package testeback;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TbCustomerAccount {

    private int idCustomer;
    private String nmCustomer;
    private String cpfCnpj;
    private boolean isActive;
    private float vlTotal;

    public TbCustomerAccount() {
    }

    public TbCustomerAccount(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public TbCustomerAccount(int idCustomer, String nmCustomer, String cpfCnpj, boolean isActive, float vlTotal) {
        this.idCustomer = idCustomer;
        this.nmCustomer = nmCustomer;
        this.cpfCnpj = cpfCnpj;
        this.isActive = isActive;
        this.vlTotal = vlTotal;
    }

    // Getters & Setters
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNmCustomer() {
        return nmCustomer;
    }

    public void setNmCustomer(String nmCustomer) {
        this.nmCustomer = nmCustomer;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public float getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(float vlTotal) {
        this.vlTotal = vlTotal;
    }

    @Override
    public String toString() {
        return "ID [ID=" + idCustomer + ", Nome=" + nmCustomer + ", CPF/CNPJ=" + cpfCnpj + ", Status=" + isActive + ", Saldo= R$" + vlTotal + "]";
    }

    // INSERT, UPDATE, DELETE
    public void insert(Connection conn) {
        String sqlInsert = "INSERT INTO tb_customer_account(id_customer, nm_customer, cpf_cnpj, is_active, vl_total) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlInsert);
            stm.setInt(1, getIdCustomer());
            stm.setString(2, getNmCustomer());
            stm.setString(3, getCpfCnpj());
            stm.setBoolean(4, getIsActive());
            stm.setFloat(5, getVlTotal());

            stm.execute();

            int[] affectedRecords = stm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    public void delete(Connection conn) {
        String sqlDelete = "DELETE FROM tb_customer_account WHERE id_Customer = ?";
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlDelete);
            stm.setInt(1, getIdCustomer());

            stm.execute();

            int[] affectedRecords = stm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    public void update(Connection conn) {
        String sqlUpdate = "UPDATE tb_customer_account SET nm_customer = ?, cpf_cnpj = ?, is_active = ?, vl_total WHERE id_customer = ?";

        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(sqlUpdate);
            stm.setString(1, getNmCustomer());
            stm.setString(2, getCpfCnpj());
            stm.setBoolean(3, getIsActive());
            stm.setFloat(4, getVlTotal());
            stm.setInt(5, getIdCustomer());

            stm.execute();

            int[] affectedRecords = stm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    public void select(Connection conn) {
        String sqlSelect = "SELECT id_customer, nm_customer, cpf_cnpj, is_active, vl_total FROM tb_customer_account";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sqlSelect);
            rs = stm.executeQuery();

            while (rs.next()) {
                this.setIdCustomer(rs.getInt("id_customer"));
                this.setNmCustomer(rs.getString("nm_customer"));
                this.setCpfCnpj(rs.getString("cpf_cnpj"));
                this.setIsActive(rs.getBoolean("is_active"));
                this.setVlTotal(rs.getFloat("vl_total"));
                System.out.println(toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }

    public void selectMedia(Connection conn) {
        String sqlSelect = "SELECT AVG(vl_total)"
                         + "FROM tb_customer_account"
                         + "WHERE vl_total >= ? AND (id_customer BETWEEN ? AND ?)";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sqlSelect);
            stm.setFloat(1, 560);
            stm.setInt(2, 1500);
            stm.setInt(3, 2700);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                this.setIdCustomer(rs.getInt("id_customer"));            
                this.setVlTotal(rs.getFloat("vl_total"));
                System.out.println("Média do Valor total: R$" + getVlTotal()+ "\n");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }
    public void selectCustomersMedia(Connection conn) {
        String sqlSelect = "SELECT id_customer, nm_customer, cpf_cnpj, is_active, vl_total FROM tb_customer_account WHERE (id_customer BETWEEN ? AND ?) AND vl_total >= ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.prepareStatement(sqlSelect);
            stm.setInt(1, 1500);
            stm.setInt(2, 2700);
            stm.setFloat(3, 560);
            rs = stm.executeQuery();
            
            while (rs.next()) {
               this.setIdCustomer(rs.getInt("id_customer"));
               this.setNmCustomer(rs.getString("nm_customer"));
               this.setCpfCnpj(rs.getString("cpf_cnpj"));
               this.setIsActive(rs.getBoolean("is_active"));
               this.setVlTotal(rs.getFloat("vl_total"));
               System.out.println("Clientes utilizados para calcular a média \n");
               System.out.println(toString());
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                System.out.print(e1.getStackTrace());
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e1) {
                    System.out.print(e1.getStackTrace());
                }
            }
        }
    }
}
