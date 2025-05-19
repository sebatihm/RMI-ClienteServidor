package com.my.company.sistemaclienteservidorrmi.client;

import com.my.company.sistemaclienteservidorrmi.entities.Doctor;
import interfaces.IDoctorInterface;

import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MedicalPanel extends JPanel {
    private JTable doctorTable;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtSpecialty, txtLicense, txtEmail;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private IDoctorInterface doctorService;
    private Doctor selectedDoctor;

    public MedicalPanel() {
        try {
            doctorService = Client.getRemoteService().getDoctorService();
            initComponents();
            loadDoctors();
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to the server: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos del formulario
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Especialidad:"), gbc);

        gbc.gridx = 1;
        txtSpecialty = new JTextField(20);
        formPanel.add(txtSpecialty, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Cédula:"), gbc);

        gbc.gridx = 1;
        txtLicense = new JTextField(20);
        formPanel.add(txtLicense, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = new JButton("Agregar");
        btnUpdate = new JButton("Actualizar");
        btnDelete = new JButton("Eliminar");
        btnClear = new JButton("Limpiar");

        btnAdd.setPreferredSize(new Dimension(100, 30));
        btnUpdate.setPreferredSize(new Dimension(100, 30));
        btnDelete.setPreferredSize(new Dimension(100, 30));
        btnClear.setPreferredSize(new Dimension(100, 30));

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Tabla de médicos
        tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Nombre", "Especialidad", "Cédula", "Correo electrónico"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        doctorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        // Eventos
        btnAdd.addActionListener(this::addDoctor);
        btnUpdate.addActionListener(this::updateDoctor);
        btnDelete.addActionListener(this::deleteDoctor);
        btnClear.addActionListener(e -> clearForm());

        doctorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = doctorTable.getSelectedRow();
                if (row >= 0) {
                    selectedDoctor = new Doctor();
                    selectedDoctor.setId((Integer) tableModel.getValueAt(row, 0));
                    selectedDoctor.setName((String) tableModel.getValueAt(row, 1));
                    selectedDoctor.setSpecialty((String) tableModel.getValueAt(row, 2));
                    selectedDoctor.setLicense((String) tableModel.getValueAt(row, 3));
                    selectedDoctor.setEmail((String) tableModel.getValueAt(row, 4));

                    txtName.setText(selectedDoctor.getName());
                    txtSpecialty.setText(selectedDoctor.getSpecialty());
                    txtLicense.setText(selectedDoctor.getLicense());
                    txtEmail.setText(selectedDoctor.getEmail());

                    btnUpdate.setEnabled(true);
                    btnDelete.setEnabled(true);
                    btnAdd.setEnabled(false);
                }
            }
        });

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void loadDoctors() {
        try {
            tableModel.setRowCount(0);
            List<Doctor> doctors = doctorService.getAllDoctors();
            for (Doctor doctor : doctors) {
                tableModel.addRow(new Object[] {
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpecialty(),
                        doctor.getLicense(),
                        doctor.getEmail()
                });
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error cargando médicos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addDoctor(ActionEvent e) {
        if (!validateForm()) return;

        try {
            Doctor doctor = new Doctor();
            doctor.setName(txtName.getText());
            doctor.setSpecialty(txtSpecialty.getText());
            doctor.setLicense(txtLicense.getText());
            doctor.setEmail(txtEmail.getText());

            doctorService.createDoctor(doctor);
            JOptionPane.showMessageDialog(this, "Médico agregado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadDoctors();
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir el médico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDoctor(ActionEvent e) {
        if (selectedDoctor == null || !validateForm()) return;

        try {
            selectedDoctor.setName(txtName.getText());
            selectedDoctor.setSpecialty(txtSpecialty.getText());
            selectedDoctor.setLicense(txtLicense.getText());
            selectedDoctor.setEmail(txtEmail.getText());

            doctorService.updateDoctor(selectedDoctor);
            JOptionPane.showMessageDialog(this, "Médico actualizado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadDoctors();
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el médico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteDoctor(ActionEvent e) {
        if (selectedDoctor == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un médico para eliminarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres eliminar al médico " + selectedDoctor.getName() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                doctorService.deleteDoctor(selectedDoctor.getId());
                JOptionPane.showMessageDialog(this, "Médico eliminado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadDoctors();
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el médico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtSpecialty.setText("");
        txtLicense.setText("");
        txtEmail.setText("");
        selectedDoctor = null;
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
    }

    private boolean validateForm() {
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre es requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtSpecialty.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Especialidad es requerida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtLicense.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cédula es requerida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!txtLicense.getText().matches("^[0-9]{6,10}$")) {
            JOptionPane.showMessageDialog(this, "Cédula debe contener entre 6 y 10 dígitos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email es requerido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "Formato de email inválido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}
