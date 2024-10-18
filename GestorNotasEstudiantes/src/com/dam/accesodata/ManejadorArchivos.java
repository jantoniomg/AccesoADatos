package com.dam.accesodata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {
    private static final String ARCHIVO = "resources/notas_estudiantes.txt";

    // Añadir un estudiante al archivo
    public void añadirEstudiante(Estudiante estudiante) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(estudiante.toString());
            bw.newLine();
            System.out.println("Estudiante añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir el estudiante: " + e.getMessage());
        }
    }

    // Mostrar todos los estudiantes almacenados en el archivo
    public void mostrarEstudiantes() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            for (Estudiante estudiante : estudiantes) {
                System.out.println(estudiante.getNombre() + " - Nota: " + estudiante.getNota());
            }
        }
    }

    // Buscar un estudiante por su nombre
    public void buscarEstudiante(String nombre) {
        List<Estudiante> estudiantes = leerEstudiantes();
        boolean encontrado = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Estudiante encontrado: " + estudiante.getNombre() + " - Nota: " + estudiante.getNota());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Estudiante no encontrado.");
        }
    }

    // Calcular la nota media de todos los estudiantes
    public void calcularMedia() {
        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para calcular la nota media.");
            return;
        }
        double suma = 0;
        for (Estudiante estudiante : estudiantes) {
            suma += estudiante.getNota();
        }
        double media = suma / estudiantes.size();
        System.out.println("La nota media de los estudiantes es: " + media);
    }

    // Leer los estudiantes desde el archivo
    private List<Estudiante> leerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    double nota = Double.parseDouble(partes[1]);
                    estudiantes.add(new Estudiante(nombre, nota));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return estudiantes;
    }
}
