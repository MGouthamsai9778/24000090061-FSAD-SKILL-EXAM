package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

/**
 * ClientDemo class demonstrates Hibernate operations on Supplier entity.
 * Performs insert and update operations on the Supplier table.
 */
public class ClientDemo {

    private static SessionFactory sessionFactory;

    /**
     * Main method to demonstrate database operations
     */
    public static void main(String[] args) {
        // Build SessionFactory from hibernate.cfg.xml
        sessionFactory = new Configuration().configure().buildSessionFactory();

        try {
            // Perform insert operation
            insertSupplier();

            // Perform update operation (assuming the inserted supplier has ID 1)
            updateSupplier(1);

        } finally {
            // Close SessionFactory
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    /**
     * Inserts a new Supplier record into the database
     */
    private static void insertSupplier() {
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = sessionFactory.openSession();

            // Begin transaction
            transaction = session.beginTransaction();

            // Create a new Supplier object
            Supplier supplier = new Supplier(
                "ABC Supplies",
                "Leading supplier of office materials",
                new Date(),
                "Active"
            );

            // Save the supplier to database
            session.save(supplier);

            // Commit transaction
            transaction.commit();

            System.out.println("Supplier inserted successfully: " + supplier);

        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close session
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Updates the name and status of a Supplier using the supplier ID
     * @param supplierId The ID of the supplier to update
     */
    private static void updateSupplier(int supplierId) {
        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = sessionFactory.openSession();

            // Begin transaction
            transaction = session.beginTransaction();

            // Retrieve the supplier by ID
            Supplier supplier = session.get(Supplier.class, supplierId);

            if (supplier != null) {
                // Update name and status
                supplier.setName("XYZ Supplies Updated");
                supplier.setStatus("Inactive");

                // Update the supplier in database
                session.update(supplier);

                // Commit transaction
                transaction.commit();

                System.out.println("Supplier updated successfully: " + supplier);
            } else {
                System.out.println("Supplier with ID " + supplierId + " not found.");
            }

        } catch (Exception e) {
            // Rollback transaction in case of error
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close session
            if (session != null) {
                session.close();
            }
        }
    }
}