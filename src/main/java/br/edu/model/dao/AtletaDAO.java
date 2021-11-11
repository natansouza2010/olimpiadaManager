package br.edu.model.dao;

import br.edu.model.Atleta;
import br.edu.model.AtletaParaOlimpico;
import br.edu.model.utils.ConnectionFactory;
import br.edu.model.utils.Deficiência;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtletaDAO implements GenericDAO<Atleta, Integer> {
    @Override
    public boolean insert(Atleta a) {
        String sql = "INSERT INTO atleta (cod, name, modality, country, birthday, deficiency) values (?,?,?,?,?,?)";
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement stmt = factory.createPreparedStatement(sql)){
            stmt.setString(1, String.valueOf(a.getCod()));
            stmt.setString(2, String.valueOf(a.getName()));
            stmt.setString(3, String.valueOf(a.getModality()));
            stmt.setString(4, String.valueOf(a.getCountry()));
            stmt.setString(5, String.valueOf(a.getBirthDate()));
            if(a instanceof AtletaParaOlimpico){
                stmt.setString(6, String.valueOf(((AtletaParaOlimpico) a).getDeficiency()));
                stmt.execute();
                stmt.close();
            }else{
                stmt.setString(6,String.valueOf(a.isParaOlímpico()));
                stmt.execute();
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Atleta findOne(Integer key) {
        return null;
    }

    @Override
    public List<Atleta> listAll() {
        String sql = "SELECT * FROM atleta";
        List<Atleta> atletas = new ArrayList<>();
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement stmt = factory.createPreparedStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Integer cod = Integer.valueOf(rs.getString("cod"));
                String name = String.valueOf(rs.getString("name"));
                String modality = String.valueOf(rs.getString("modality"));
                String country = String.valueOf(rs.getString("country"));
                LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
                if(!rs.getString("deficiency").equals("false")){
                    String d = String.valueOf(rs.getString("deficiency"));
                    String defi = String.valueOf(d);
                    Atleta a1 = new AtletaParaOlimpico(cod,name,modality,country,birthday,defi);
                    atletas.add(a1);

                }else{
                    Atleta a1 = new Atleta(cod,name,modality,country,birthday);
                    atletas.add(a1);
                }

            }
                return atletas;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Atleta a) {
        String sql = "UPDATE atleta SET name = ? , modality = ?  ,country = ? , birthday = ? , deficiency = ? WHERE cod = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement stmt = factory.createPreparedStatement(sql)){
            stmt.setString(6, String.valueOf(a.getCod()));
            stmt.setString(1, String.valueOf(a.getName()));
            stmt.setString(2, String.valueOf(a.getModality()));
            stmt.setString(3, String.valueOf(a.getCountry()));
            stmt.setString(4, String.valueOf(a.getBirthDate()));
            if(a instanceof AtletaParaOlimpico){
                stmt.setString(5, String.valueOf(((AtletaParaOlimpico) a).getDeficiency()));
                stmt.executeUpdate();
                stmt.close();
            }else{
                stmt.setString(5,String.valueOf(a.isParaOlímpico()));
                stmt.executeUpdate();
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Integer key) {
        String sql = "DELETE FROM atleta WHERE cod = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try(PreparedStatement stmt = factory.createPreparedStatement(sql)){
            stmt.setString(1,String.valueOf(key));
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
