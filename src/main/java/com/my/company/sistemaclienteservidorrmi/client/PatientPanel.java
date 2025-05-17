package com.my.company.sistemaclienteservidorrmi.client;

import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import com.my.company.sistemaclienteservidorrmi.entities.Patient;
import interfaces.IPatientInterface;

public class PatientPanel extends JPanel {
    private JTable patientTable;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtCurp, txtPhone, txtEmail;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private IPatientInterface patientService;
    private Patient selectedPatient;

    public PatientPanel() {
        try {
            patientService = Client.getRemoteService().getPatientService();
            initComponents();
            loadPatients();
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("CURP:"), gbc);

        gbc.gridx = 1;
        txtCurp = new JTextField(20);
        formPanel.add(txtCurp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Phone:"), gbc);

        gbc.gridx = 1;
        txtPhone = new JTextField(20);
        formPanel.add(txtPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

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

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "CURP", "Teléfono", "Correo electrónico"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        patientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        // Events
        btnAdd.addActionListener(this::addPatient);
        btnUpdate.addActionListener(this::updatePatient);
        btnDelete.addActionListener(this::deletePatient);
        btnClear.addActionListener(e -> clearForm());

        patientTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = patientTable.getSelectedRow();
                if (row >= 0) {
                    selectedPatient = new Patient();
                    selectedPatient.setId((Integer) tableModel.getValueAt(row, 0));
                    selectedPatient.setName((String) tableModel.getValueAt(row, 1));
                    selectedPatient.setCURP((String) tableModel.getValueAt(row, 2));
                    selectedPatient.setPhone((String) tableModel.getValueAt(row, 3));
                    selectedPatient.setEmail((String) tableModel.getValueAt(row, 4));

                    txtName.setText(selectedPatient.getName());
                    txtCurp.setText(selectedPatient.getCURP());
                    txtPhone.setText(selectedPatient.getPhone());
                    txtEmail.setText(selectedPatient.getEmail());

                    btnUpdate.setEnabled(true);
                    btnDelete.setEnabled(true);
                    btnAdd.setEnabled(false);
                }
            }
        });

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    private void loadPatients() {
        try {
            tableModel.setRowCount(0);
            List<Patient> patients = patientService.getAllPatients();
            for (Patient patient : patients) {
                tableModel.addRow(new Object[] {
                    patient.getId(),
                    patient.getName(),
                    patient.getCURP(),
                    patient.getPhone(),
                    patient.getEmail()
                });
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Error loading patients: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPatient(ActionEvent e) {
        if (!validateForm()) return;

        try {
            Patient patient = new Patient();
            patient.setName(txtName.getText());
            patient.setCURP(txtCurp.getText());
            patient.setPhone(txtPhone.getText());
            patient.setEmail(txtEmail.getText());

            patientService.createPatient(patient);
            JOptionPane.showMessageDialog(this, "Paciente agregado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadPatients();
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir el paciente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePatient(ActionEvent e) {
        if (selectedPatient == null || !validateForm()) return;

        try {
            selectedPatient.setName(txtName.getText());
            selectedPatient.setCURP(txtCurp.getText());
            selectedPatient.setPhone(txtPhone.getText());
            selectedPatient.setEmail(txtEmail.getText());

            patientService.updatePatient(selectedPatient);
            JOptionPane.showMessageDialog(this, "Paciente actualizado correctamente. ", "Info", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            loadPatients();
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el paciente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePatient(ActionEvent e) {
        if (selectedPatient == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un paciente para eliminarlo.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres eliminar este paciente? " + selectedPatient.getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            try {
                patientService.deletePatient(selectedPatient.getId());
                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente. ", "Info", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadPatients();
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el paciente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtCurp.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        selectedPatient = null;
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
    }

    private boolean validateForm() {
        if (txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtCurp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CURP is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!txtCurp.getText().matches("^[A-Z0-9]{18}$")) {
            JOptionPane.showMessageDialog(this, "CURP must be 18 alphanumeric characters", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtPhone.getText().trim().length() < 10) {
            JOptionPane.showMessageDialog(this, "Phone must be at least 10 digits", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}
