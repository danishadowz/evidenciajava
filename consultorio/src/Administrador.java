import java.io.*;
import java.util.*;
public class Administrador {
    public HashMap <String , String>  usuarios = new HashMap<>() ;
    public  void registar_usuario (HashMap<String,String>contactos){
        cargar_usuarios(usuarios);
        Scanner entrada  = new Scanner(System.in);
        System.out.println("Escribe tu usuario");
        String a= entrada.next();


        System.out.println("Registra tu contraseña");

        String  b = entrada.next();
        if((usuarios.containsKey(b))&&(usuarios.containsValue(a))){System.out.println("El usuario o contraseña ya existe");}
        else { contactos.put(b,a);
            System.out.println("te has registrado con exito");
            guardar_usuarios(usuarios);}}
    public String Eliminar_administrador(HashMap<String,String> usuarios){
        cargar_usuarios(usuarios);
        String a;
        Scanner entrada = new Scanner(System.in);
        System.out.println("¿Que usuario desear dar de baja?");
        a = entrada.nextLine();
        return    usuarios.remove(a);}
    public void cargar_usuarios (HashMap<String,String>usuarios){
        String arch = "src\\usuarios.txt";
        BufferedReader bufferedReader = null;
        try {
            File file = new File("src\\usuarios.txt");
            bufferedReader = new BufferedReader(new FileReader(arch));
            String a  ;
            while ((a = bufferedReader.readLine()) != null) {
                String[]  lista = a.split(":");

                String usuario = lista[0];
                String contraseña = lista[1];
                if(!usuario.equals("")&& !contraseña.equals("")){
                    usuarios.put(usuario,contraseña);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            if(bufferedReader!=null) try {bufferedReader.close();}catch (Exception e){}
        }
    }
    public  void lista_usuarios(HashMap<String,String>usuarios){

        cargar_usuarios(usuarios);

        System.out.println("       Contactos: ");
        Iterator it = usuarios.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            System.out.println( pair.getValue()  + " : " + pair.getKey());
        }

    }
    public  void modificar_usuarios(HashMap<String,String>usuarios){
        Scanner entrada = new Scanner(System.in);
        System.out.println("Nota: Solo puedes modificar tu usuario, para modificar la contraseña elimina el usuario y vuelvelo a registrar");
        cargar_usuarios(usuarios);
        System.out.println("Escribe tu usuario");
        String a= entrada.nextLine();


        System.out.println("Registra tu contraseña");

        String  b = entrada.nextLine();
        System.out.println("Escribe tu nuevo usuario");
        String c = entrada.nextLine();

        if (usuarios.containsKey(b)){usuarios.replace(b,c);}
        guardar_usuarios(usuarios);

    }
    public  void guardar_usuarios(HashMap<String, String> usuarios) {
        String arch = "src\\usuarios.txt" ;
        File  file= new File(arch);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry :
                    usuarios.entrySet()) {
                bufferedWriter.write(entry.getKey() + ":"
                        + entry.getValue());

                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
        }   catch (IOException e) {
            e.printStackTrace();

        }  finally {
            try {
                bufferedWriter.close();
            } catch (Exception e){}
        }
    }

    public void inicio_admin (HashMap<String, String> usuarios) {
        cargar_usuarios(usuarios);
        Scanner entrada  = new Scanner(System.in);
        System.out.println("Escribe tu usuario");
        String a= entrada.next();
        System.out.println("Escribe tu contraseña");
        String b= entrada.next();



        if((usuarios.containsKey(b))&&(usuarios.containsValue(a))){System.out.println("Has iniciado sesion");
            System.out.println("------------------------------------------------");

            menu_global();




        }else {
            System.out.println("El usuario no existe");
            System.out.println("Registrate: ");
            registar_usuario(usuarios);
            System.out.println("inicia sesion: ");
            inicio_admin(usuarios);
        }

    }
    public void menu_global(){
        try{ int r1,r2;
            Scanner entrada = new Scanner(System.in);
            System.out.println("Bienvenido presiona cualquiera de las siguientes opciones: ");
            System.out.println("1 para entrar al menu de administradores"+ "\n"
                    +"2 para el menu de pacientes"+"\n"
                    +"3  Para el menu de doctores " + "\n"
                    +"4  Para el menu de citas   " + "\n"
                    +"0 Para salir");
            r1 = entrada.nextInt();
            if(r1==1){
                menu_admin();

                menu_global();
            }
            else  if(r1==2){
                Pacientes pacientes = new Pacientes();
                pacientes.menu_pacientes();
                menu_global();
            }
            else if(r1==3){
                Doctores doctores = new Doctores();
                doctores.menu_doctores();
                menu_global();
            }
            else if(r1==4){
                Cita cita = new Cita();
                cita.menu_citas();
                menu_admin();}

            else if (r1 == 0){
            }
            if((r1 < 0) || (r1> 4)) {
                throw new IllegalArgumentException("Esta opcion no existe.");
            }
        }catch (InputMismatchException e)  {
            System.out.println("Escribiste una letra,  debes escribir un numero" );
            inicio_admin(usuarios);}
        catch (IllegalArgumentException e) {
            System.out.println("Esta opcion no existe");
            inicio_admin(usuarios);}
    }
    public void menu_admin(){
        Scanner entrada = new Scanner(System.in);
        try{ int r1,r2;

            System.out.println("Bienvenido presiona cualquiera de las siguientes opciones: ");
            System.out.println("1 para registrar a  un usuario: "+ "\n"
                    +"2 para mostrar los usuarios"+"\n"
                    +"3 Eliminar un usuario " + "\n"
                    +"4 Modificar un usuario "+ "\n"
                    +"0 Para regresar al menu principal");
            r1 = entrada.nextInt();
            if(r1==1){
                registar_usuario(usuarios);
                menu_admin();
            }
            else  if(r1==2){
                lista_usuarios(usuarios);
                menu_admin();
            }
            else if(r1==3){
                Eliminar_administrador(usuarios);
                guardar_usuarios(usuarios);
                menu_admin();
            } else if(r1==4){
                modificar_usuarios(usuarios);
                menu_admin();}
            else if(r1 == 0){menu_global();
            }
            else if((r1 < 0) || (r1> 4)) {
                throw new IllegalArgumentException("Esta opcion no existe.");
            }
        }catch (InputMismatchException e)  {
            System.out.println("Escribiste una letra,  debes escribir un numero" );
            menu_admin();}
        catch (IllegalArgumentException e) {
            System.out.println("Esta opcion no existe");
            menu_admin();}
    }

}

