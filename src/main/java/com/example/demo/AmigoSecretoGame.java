package com.example.demo;

import java.util.*;

public class AmigoSecretoGame {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<String> participants = new ArrayList<>();
		boolean sorteoRealizado = false;
		boolean entrarDo = true;

		while (entrarDo) {
			System.out.println("\n************************ Menú: ************************");
			System.out.println("1. Ingresar participante");
			System.out.println("2. Listar participantes inscritos");
			System.out.println("0. Empezar sorteo");
			System.out.print("Ingrese su opción: ");
			int opcion = 3;
			try {
				opcion = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nError: Ingrese un valor numérico válido.\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				scanner.nextLine(); // Consumir la entrada inválida
				continue;
			}
			scanner.nextLine(); // Consumir la nueva línea después de leer el entero
			switch (opcion) {
				case 1:
					System.out.print("Ingrese el nombre del participante: ");
					String nombre = scanner.nextLine();
					participants.add(nombre);
					System.out.println("*******************************************************");
					System.out.println("==============Participante añadido: " + nombre + " ==============");
					break;
				case 2:
					System.out.println(
							"-------------------------------------- Participantes inscritos: --------------------------------------");
					for (String participant : participants) {

						System.out.println("*.- " + participant);
					}
					System.out.println("*.- Cantidad de participantes: " + participants.size() +
							"\n-------------------------------------------------------------------------------------------------------");
					break;
				case 0:
					if (participants.size() < 2) {
						System.out.println("Debe haber al menos dos participantes para realizar el sorteo.");
						continue;
					}

					if (!sorteoRealizado) {
						sorteoRealizado = true;
						List<String> seatingOrder = new ArrayList<>(participants);
						Collections.shuffle(seatingOrder);
						Collections.shuffle(participants);

						for (int i = 0; i < participants.size(); i++) {
							if (seatingOrder.get(i).equals(participants.get(i))) {
								Collections.swap(seatingOrder, i, (i + 1) % participants.size());
							}
							clearConsole();
							System.out.println("Empieza el sorteo...");
							System.out.println("Le toca sentarse a: " + seatingOrder.get(i));
							System.out.print("Presiona Enter para continuar...");
							scanner.nextLine();
							clearConsole();

							System.out.println(seatingOrder.get(i) + " te toca obsequiarle a: " + participants.get(i));
							System.out.print("Presiona Enter para continuar...");
							scanner.nextLine();
							clearConsole();
						}

						System.out.println("Sorteo finalizado.");
						entrarDo = false;
					} else {
						System.out.println("El sorteo ya ha sido realizado.");
					}
					break;
				default:
					System.out.println(
							"\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nError: Ingrese un valor válido.\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
		}
	}

	private static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
