package com.acuevas.sudokus.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MyFileReader implements IFileReader {

	@Override
	public void readFile(File file) {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			Integer level;
			String description;
			String uncompletedSudoku;
			String completedSudoku;

			while ((line = bufferedReader.readLine()) != null) {
				if (line.substring(0).equals("%")) {
					line.trim();
					level = Integer.parseInt(line.substring(2));
					description = line.substring(3, line.length());
					uncompletedSudoku = bufferedReader.readLine();
					completedSudoku = bufferedReader.readLine();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/* @formatter:off
	 * static ArrayList<Alumno> leerArchivo(File archivo) {
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Alumno> listAlumnos = new ArrayList<>();
        Alumno alumno;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split(";");
                alumno = new Alumno(datos[0], datos[1], datos[2], datos[3], Integer.parseInt(datos[4]));
                listAlumnos.add(alumno);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al encontrar el archivo " + ex.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el archivo " + ex.getMessage());
            }
        }
        return listAlumnos;
        @formatter:on
	 */

}
