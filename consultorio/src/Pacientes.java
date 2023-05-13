import java.util.*;
import java.io.*;

public class Pacientes {
    public ArrayList<String> listaPaciente= new ArrayList<>();
    public ArrayList<String> lista_id= new ArrayList<>();



    public void agregar_pacientes( ArrayList<String> listaPaciente) {

        Scanner scanner = new Scanner(System.in);
        cargar_id(lista_id);
        System.out.println("Escribe el nombre del paciente");
        String a = scanner.nextLine();
        System.out.println("Escribe el id del paciente:");
        String b = scanner.nextLine();
        if (lista_id.contains(b)) {
            System.out.println("El id ya existe vuelve a agregarlo");
        }
        else{
            cargar_id(lista_id);
            lista_id.add(b);

            guardar_id(lista_id);
            cargar_pacientes(listaPaciente);
            listaPaciente.add(a);

            guardar_pacientes(listaPaciente);};

    }
    public void guardar_pacientes(ArrayList<String>listaPaciente){
        String arch ="src\\pacientes.txt" ;
        File  file= new File(arch);
        BufferedWriter bufferedWriter = null;

        try{

            PrintWriter write = new PrintWriter(file);
            for (String name: listaPaciente){
                write.println(name);
            }
            write.close();
        }
        catch(IOException exe){
            System.out.println("Cannot create file");
        }
    }

    public void cargar_pacientes(ArrayList<String>listaPaciente){

        ArrayList<String> arr = listaPaciente;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\pacientes.txt" ))){

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] values = sCurrentLine.split(sCurrentLine);
                arr.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void guardar_id(ArrayList<String>lista_id){
        String arch = "src\\id_pacientes.txt" ;
        File  file= new File(arch);
        BufferedWriter bufferedWriter = null;

        try{

            PrintWriter write = new PrintWriter(file);
            for (String name: lista_id){
                write.println(name);
            }
            write.close();
        }
        catch(IOException exe){
            System.out.println("Cannot create file");
        }
    }

    public void cargar_id(ArrayList<String>listaPaciente){

        ArrayList<String> arr = listaPaciente;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\id_pacientes.txtdani" ))){

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String[] values = sCurrentLine.split(sCurrentLine);
                arr.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void mostrar_pacientes(){
        cargar_pacientes(listaPaciente);
        cargar_id(lista_id);
        Iterator iterator1 = lista_id.iterator();
        Iterator iterator = listaPaciente.iterator();

        System.out.println("                    Lista de pacientes                          ");
        System.out.println("----------------------------------------------------------------");
        while (iterator.hasNext()&&iterator1.hasNext() ){
            System.out.println("Paciente: " + iterator.next().toString() + " ID: "+iterator1.next());
        }
        System.out.println("----------------------------------------------------------------");
    }
    public void  eliminar_pacientes(ArrayList<String>listaPaciente){
        mostrar_pacientes();
        cargar_pacientes(listaPaciente);
        cargar_id(lista_id);
        lista_id.clear();
        guardar_id(lista_id);

        listaPaciente.clear();
        guardar_pacientes(listaPaciente);


    }
    public void menu_pacientes(){
        Scanner entrada = new Scanner(System.in);
        try{ int r1,r2;

            System.out.println("Bienvenido presiona cualquiera de las siguientes opciones: ");
            System.out.println("1 para registrar a  un paciente: "+ "\n"
                    +"2 para mostrar la lista de pacientes"+"\n"
                    +"3 Eliminar a todos los paientes " + "\n"
                    +"0 Para regresar al menu principal");
            r1 = entrada.nextInt();
            if(r1==1){
                agregar_pacientes(listaPaciente);
                System.out.println("Presiona 1 para volver al menu");
                menu_pacientes();
            }
            else  if(r1==2){
                mostrar_pacientes();
                menu_pacientes();
            }
            else if(r1==3){
                eliminar_pacientes(listaPaciente);
                menu_pacientes();
            }
            else if(r1 == 0){
                Administrador administrador = new Administrador();
                administrador.menu_global();
            }
            else if((r1 < 0) || (r1> 3)) {
                throw new IllegalArgumentException("Esta opcion no existe.");
            }
        }catch (InputMismatchException e)  {
            System.out.println("Escribiste una letra,  debes escribir un numero" );
            menu_pacientes();}
        catch (IllegalArgumentException e) {
            System.out.println("Esta opcion no existe");
            menu_pacientes();}
    }
}
