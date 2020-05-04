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
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentDaoFunctionalTest {

    final Logger logger = LogManager.getLogger(StudentDaoFunctionalTest.class);

    private StudentDAO studentDAO;

    @Test
    public void findById() {
        Student student = null;
        int testId = 1;

        try {
            studentDAO = new StudentDAOImpl();
            student = studentDAO.findById(testId);

            assertEquals(student.getName(), "Mike");
        } catch (SQLException ex) {
            logger.error("Ocurrió una excepción", ex);
        }
    }

    @Test
    public void findAll() {
        List<Student> studentList = null;
        try {
            studentDAO = new StudentDAOImpl();
            studentList = studentDAO.findAll();

            assertTrue(studentList.size() > 1);
        } catch (SQLException ex) {
            logger.error("Ocurrió una excepción", ex);
        }
    }

    @Test
    public void save() {
        Student student1 = new Student();
        Student studentSaved = null;
        Random objGenerator = new Random();

        student1.setName("New Student "+ objGenerator.nextInt(100));
        student1.setCourse("Progra IV");
        student1.setRating("A+");

        try {
            studentDAO = new StudentDAOImpl();
            studentSaved = studentDAO.save(student1);

            assertEquals(studentSaved.getName() , student1.getName());
        } catch (SQLException ex) {
            logger.error("Ocurrió una excepción", ex);
        }
    }

    @Test
    public void update() {
        Student student = new Student();
        Student studentUpdated = null;
        Random objGenerator = new Random();

        try {
            studentDAO = new StudentDAOImpl();

            student.setId(2);
            student.setName("Student Updated "+ objGenerator.nextInt(100));
            student.setCourse("Progra IV");
            student.setRating("A+");

            studentUpdated = studentDAO.update(student);
            assertEquals(studentUpdated.getName() , student.getName());
        } catch (SQLException ex) {
            logger.error("Ocurrió una excepción", ex);
        }
    }
}
