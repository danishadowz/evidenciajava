import java.io.*;
import java.util.*;

public class Cita {
    public ArrayList<String> lista_citas = new ArrayList<>();
    public void agendar_cita(ArrayList<String>lista_citas){
        Pacientes pacientes = new Pacientes();
        Doctores doctores = new Doctores();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        pacientes.mostrar_pacientes();
        doctores.mostrar_doctor(doctores.lista_doctores);
        System.out.println("Escribe el nombre del paciente que vas a agendar");
        String nombre_paciente = scanner.nextLine();
        System.out.println("Escribe el motivo de la consulta");
        String motivo = scanner1.nextLine();
        System.out.println("Escribe el nombre del doctor que vas a agendar");
        String nombre_doctor = scanner.nextLine();

        System.out.println("Escribe la fecha y hora de la cita");
        String fecha = scanner.nextLine();

        if((pacientes.listaPaciente.contains(nombre_paciente))&&(doctores.lista_doctores.contains(nombre_doctor))){
            cargar_citas(lista_citas);
            String c = " Doctor: "+ nombre_doctor + " " + "Fecha: "+fecha;
            String d = "Paciente: " + nombre_paciente + " " +"motivo: " + motivo;
            lista_citas.add(d + c);
            System.out.println(lista_citas);
            guardar_citas(lista_citas);
        } else{System.out.println("El paciente o el doctor no esta registrado");}

    }
    public void guardar_citas(ArrayList<String>lista_citas){
        String arch = "src\\citas.txt" ;
        File file= new File(arch);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for(String str: lista_citas) {
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

    public void cargar_citas(ArrayList<String>lista_citas){

        ArrayList<String> arr = lista_citas;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\citas.txt"))){

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public void mostrar_citas(ArrayList<String>lista_citas){
        cargar_citas(lista_citas);

        Iterator iterator = lista_citas.iterator();

        System.out.println("                  Lista de citas agendadas                      ");
        System.out.println("----------------------------------------------------------------");
        while (iterator.hasNext() ){
            System.out.println("Cita: " + iterator.next().toString() );
        }
        System.out.println("----------------------------------------------------------------");
    }
    public void  eliminar_citas(ArrayList<String>lista_citas){
        cargar_citas(lista_citas);
        lista_citas.clear();
        guardar_citas(lista_citas);
    }
    public  void menu_citas(){
        Scanner entrada = new Scanner(System.in);
        try{ int r1,r2;

            System.out.println("Bienvenido presiona cualquiera de las siguientes opciones: ");
            System.out.println("1 para crear una cita: "+ "\n"
                    +"2 para mostrar la lista de las citas"+"\n"
                    +"3 Eliminar todas las citas " + "\n"
                    +"0 Para Regresar al menu principal");
            r1 = entrada.nextInt();
            if(r1==1){
                agendar_cita(lista_citas);
                menu_citas();
            }
            else  if(r1==2){
                mostrar_citas(lista_citas);
                menu_citas();
            }
            else if(r1==3){
                eliminar_citas(lista_citas);

                menu_citas();
            }
            else if(r1 == 0){Administrador administrador = new Administrador();
                administrador.menu_global();
            }
            else if((r1 < 0) || (r1> 3)) {
                throw new IllegalArgumentException("Esta opcion no existe.");
            }
        }catch (InputMismatchException e)  {
            System.out.println("Escribiste una letra,  debes escribir un numero" );
            menu_citas();}
        catch (IllegalArgumentException e) {
            System.out.println("Esta opcion no existe");
            menu_citas();}
    }
}