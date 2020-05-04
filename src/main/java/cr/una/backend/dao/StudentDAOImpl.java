/*
 *
 * Copyright (C)  2020  mike.education
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Universidad Nacional de Costa Rica, Prof: Maikol Guzman Alan.
 */

package cr.una.backend.dao;

import cr.una.backend.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    final Logger logger = LogManager.getLogger(StudentDAOImpl.class);

    private Connection dbConnection = null;
    private Statement statement = null;

    public StudentDAOImpl() throws SQLException {
        dbConnection = JdbcUtil.getDBConnection();
        statement = (Statement) dbConnection.createStatement();
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        String selectTableSQL = "SELECT ID, NAME, COURSE, RATING from student "
                + "WHERE ID=" + id;

        try {
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);

            if (rs != null) {
                student = new Student();
                while (rs.next()) {

                    student.setId(rs.getInt("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setCourse(rs.getString("COURSE"));
                    student.setRating(rs.getString("RATING"));

                }
            }

        } catch (SQLException ex) {
            logger.debug(ex);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

        }

        return student;
    }

    @Override
    public Student save(Student student) {
        String insertTableSQL = "INSERT INTO student"
                + "(NAME, COURSE, RATING) " + "VALUES"
                + "('" + student.getName() + "','"
                + student.getCourse() + "','" + student.getRating() + "')";

        try {
            // execute insert SQL stetement
            statement.executeUpdate(insertTableSQL);

        } catch (SQLException ex) {
            logger.debug(ex);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

        }

        return student;
    }

    @Override
    public Student update(Student student) {
        String updateTableSQL = "UPDATE student ";
        updateTableSQL += String.format("SET name = '%s', ", student.getName());
        updateTableSQL += String.format("course = '%s', ", student.getCourse());
        updateTableSQL += String.format("rating = '%s' ", student.getRating());
        updateTableSQL += String.format("WHERE id = %x;", student.getId());

        try {
            // execute insert SQL stetement
            statement.executeUpdate(updateTableSQL);

        } catch (SQLException ex) {
            logger.debug(ex);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

        }

        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = null;
        String selectTableSQL = "SELECT ID, NAME, COURSE, RATING from student";

        try {
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);

            if (rs != null) {
                studentList = new ArrayList<>();
                while (rs.next()) {
                    Student student = new Student();

                    student.setId(rs.getInt("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setCourse(rs.getString("COURSE"));
                    student.setRating(rs.getString("RATING"));

                    studentList.add(student);

                }
            }

        } catch (SQLException ex) {
            logger.debug(ex);
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    logger.debug(ex);
                }
            }

        }

        return studentList;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
