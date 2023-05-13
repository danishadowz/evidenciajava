import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public  class Doctores {
    public ArrayList<String> lista_doctores= new ArrayList<>();
    public ArrayList<String> lista_departamento= new ArrayList<>();
    public ArrayList<String> lista_id = new ArrayList<>();
    public void agregar_doctores( ArrayList<String> listaPaciente) {
        cargar_doctores(lista_doctores);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el nombre del Doctor/a");
        String a = scanner.nextLine();




        lista_doctores.add(a);

        guardar_doctores(lista_doctores);
        agregar_departamentoyID();



    }
    public void guardar_doctores(ArrayList<String>lista_doctores){
        String arch = "src\\doctores.txt" ;
        File file= new File(arch);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for(String str: lista_doctores) {
                bufferedWriter.write(str + System.lineSeparator());
            }
            bufferedWriter.close();
        }   catch (IOException e) {
            e.printStackTrace();

        }  finally {
            try {
                bufferedWriter.close();
            } catch (Exception e){}
        }
    }
    public void guardar_departamento(ArrayList<String>lista_departamento){
        String arch = "src\\departamento.txt" ;
        File  file= new File(arch);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for(String str: lista_departamento) {
                bufferedWriter.write(str + System.lineSeparator());
            }
            bufferedWriter.close();
        }   catch (IOException e) {
            e.printStackTrace();

        }  finally {
            try {
                bufferedWriter.close();
            } catch (Exception e){}
        }
    }
    public  void agregar_departamentoyID(){
        Scanner entrada =  new Scanner(System.in);

        System.out.println("Escribe el id del doctor:");
        String b = entrada.nextLine();
        System.out.println("Escribe la especialidad del doctor:");
        String c = entrada.nextLine();
        if (lista_id.contains(b)) {
            System.out.println("El id ya existe vuelve a agregarlo");
        }
        else{
            String agregar = "ID: " + b +" "+" Departamento: " + c;
            lista_id.add(b);
            cargar_departamentos(lista_departamento);
            lista_departamento.add(agregar);

            guardar_departamento(lista_departamento);
        }

    }
    public void cargar_doctores(ArrayList<String>lista_doctores){


        ArrayList<String> arr = lista_doctores;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\doctores.txt"))){

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void cargar_departamentos(ArrayList<String>lista_departamento){


        ArrayList<String> arr = lista_departamento;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\departamento.txt"))){

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void mostrar_doctor(ArrayList<String>lista_doctores){
        cargar_doctores(lista_doctores);
        cargar_departamentos(lista_departamento);

        Iterator iterator = lista_doctores.iterator();
        Iterator iterator1 = lista_departamento.iterator();
        System.out.println( "          Lista de doctores y su departamento         ");
        System.out.println("----------------------------------------------------------------");
        while (iterator.hasNext()&&iterator1.hasNext()){
            System.out.println("Doctor " + iterator.next() +" " + iterator1.next() );
        }

    }
    public  void  eliminar_doctoresydep(){
        cargar_doctores(lista_doctores);
        lista_doctores.clear();
        guardar_doctores(lista_doctores);
        cargar_departamentos(lista_departamento);
        lista_departamento.clear();
        guardar_departamento(lista_departamento);
    }
    public  void  menu_doctores(){
        Scanner entrada = new Scanner(System.in);
        try{ int r1,r2;

            System.out.println("Bienvenido presiona cualquiera de las siguientes opciones: ");
            System.out.println("1 para registrar a  un doctor y su departamento "+ "\n"
                    +"2 para mostrar la lista de doctores"+"\n"
                    +"3 Eliminar a todos los doctores" + "\n"
                    +"0 Para Regresar al menu principal");
            r1 = entrada.nextInt();
            if(r1==1){
                agregar_doctores(lista_doctores);

                System.out.println("Presiona 1 para volver al menu");
                r2 = entrada.nextInt();
                if(r2==1){menu_doctores();}
            }
            else  if(r1==2){
                mostrar_doctor(lista_doctores);
                System.out.println("Presiona 1 para volver al menu");
                r2 = entrada.nextInt();
                if(r2==1){menu_doctores();}
            }
            else if(r1==3){
                eliminar_doctoresydep();
                System.out.println("Presiona 1 para volver al menu");
                menu_doctores();
            }
            else if(r1 == 0){Administrador administrador = new Administrador();
                administrador.menu_global();
            }
            else if((r1 < 0) || (r1> 3)) {
                throw new IllegalArgumentException("Esta opcion no existe.");
            }
        }catch (InputMismatchException e)  {
            System.out.println("Escribiste una letra,  debes escribir un numero" );
            menu_doctores();}
        catch (IllegalArgumentException e) {
            System.out.println("Esta opcion no existe");
            menu_doctores();}
    }

}