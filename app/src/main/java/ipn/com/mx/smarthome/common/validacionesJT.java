package ipn.com.mx.smarthome.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validacionesJT {
    String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz ";
    String numeros = "1234567890";
    String letMail = "@_-";
    String letSign = "-+";
    String letPoint = ".";
    String letSpecial = "{}[]¨*°!#$%&/()=?¡{},;:´'¿!#$%&/()°|¬~^`\\´¡¢£¤¥§¨©ª«¬®¯°±´µ¶·¸º»¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÑÒÓÔÕÖØÙÚÛÜßàáâãäåæçèéêëìíîïñòóôõö÷øùúûüÿ₫¦­²³¼½¾ÐðÞþ×‘OO'“OO”—O–O‚O„O;αΓβΛγΣπΠσΩς<>≠′≤″≥∂≡∫≈∑∞∏√•€™†ąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝ‡◊‰←↑→↓♠♣♥♦‹›☺☻♥♦♣♠•◘○◙♂♀♪♫☼►◄↕‼¶§▬↨↑↓→←∟↔▲▼¡“#$%&‘()*,–/:;<=>¿\\^`{|}~⌂ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤ÁÂÀ©╣║╗╝¢¥┐└┴┬├─┼ãÃ╚╔╩╦╠═╬¤ðÐÊËÈıÍÎÏ┘┌█▄¦Ì▀ÓßÔÒõÕµþÞÚÛÛýÝ¯´­±‗¾¶§÷¸°¨•¹³²■ĀāĂăĄ�ΪΫάέήί̴̵̶̷̸̡̢̧̨̜̝̞̟̠̣̤̥̦̩̪̫̬̭̮̯̰̱̲̳̹̺̻̼͇͈͉͍͎̽̾̿̀́͂̓̈́͆͊͋͌ͅ͏͓͔͕͖͙͚͐͑͒͗͛͛ͣͤͥͥͧͨͩͪͫͬͭͮͯ͘͜͟͢͞͠͡ͰͱͲͳʹ͵Ͷͷ͸͹ͺͻͼͽ;Ϳ΀΁΂΃΄΅·΋Ό΍ΎΏΐΓΔΘΙΚΛΜΝΞΟΠΡ΢ΣΤΥΦΧΨΩΪΫάέήίΰαβγδεζɗ";

    char letrA[] = letras.toCharArray();
    char numA[] = numeros.toCharArray();
    char mailA[] = letMail.toCharArray();
    char signo[] = letSign.toCharArray();
    char punto[] = letPoint.toCharArray();
    char special[] = letSpecial.toCharArray();

    boolean vacio = true, mai;
    int cLetras, cNumeros, cMail, cSigno, cPunto, cEspecial;

    public void evaluar(String k) {
        cLetras = 0;
        cNumeros = 0;
        cMail = 0;
        cSigno = 0;
        cPunto = 0;
        cEspecial=0;
        for (int i = 0 ; i < k.length(); i++) {//evaluador
            for (int j = 0; j < letras.length(); j++) {
                if (k.charAt(i) == letrA[j]) {//letras
                    cLetras++;
                } else if (j < numeros.length() && k.charAt(i) == numA[j]) {//numeros
                    cNumeros++;
                } else if (j < letMail.length() && k.charAt(i) == mailA[j]) {//numeros
                    cMail++;
                } else if (j < letSign.length() && k.charAt(i) == signo[j]) {//numeros y signo -
                    cSigno++;
                } else if (j == 0 && k.charAt(i) == punto[j]) {//numeros y signo -
                    cPunto++;
                } else if (j < letSpecial.length() && k.charAt(i) == special[j]) {//numeros y signo -
                    cEspecial++;
                }
            }

        }
    }

    public boolean soloLetras(String s){
        evaluar(s);
//        int cLetras, cNumeros, cMail, cSigno, cPunto;
        System.out.println("Letras: "+cLetras);
        System.out.println("Numeros: "+cNumeros);
        System.out.println("Correo: "+cMail);
        System.out.println("Signo: "+cSigno);
        System.out.println("Puntos: "+cPunto);
        System.out.println("Especial: "+cEspecial);
        if(cLetras != 0 && cNumeros ==0 && cMail == 0 && cSigno ==0 && cPunto==0 && cEspecial==0){
            mai = true;
        }else{
            mai = false;
        }
        System.out.println("Solo letras: "+mai);
        return mai;
    }

    public boolean soloNumeros(String s){
        evaluar(s);
//        int cLetras, cNumeros, cMail, cSigno, cPunto;
        System.out.println("Letras: "+cLetras);
        System.out.println("Numeros: "+cNumeros);
        System.out.println("Correo: "+cMail);
        System.out.println("Signo: "+cSigno);
        System.out.println("Puntos: "+cPunto);
        System.out.println("Especial: "+cEspecial);
        if(cNumeros != 0 && cLetras==0  && cMail==0 && cSigno==0 && cPunto==0 && cEspecial==0){
            mai = true;
        }else{
            mai = false;
        }
        System.out.println("Solo numeros: "+mai);
        return mai;
    }

    public boolean sinEspecial(String s){
        evaluar(s);
//        int cLetras, cNumeros, cMail, cSigno, cPunto;
        System.out.println("Letras: "+cLetras);
        System.out.println("Numeros: "+cNumeros);
        System.out.println("Correo: "+cMail);
        System.out.println("Signo: "+cSigno);
        System.out.println("Puntos: "+cPunto);
        System.out.println("Especial: "+cEspecial);
        if((cLetras!=0 || cNumeros!=0) && cMail==0 && cSigno==0 && cPunto==0 && cEspecial==0){
            mai = true;
        }else{
            mai = false;
        }
        System.out.println("Sin especial: "+mai);
        return mai;
    }

    public boolean soloMail(String correo) {
        int arroba = 0,
                punto = 0,
                letra = 0,
                largo = correo.trim().length();

        evaluar(correo);
        System.out.println("Letras: "+cLetras);
        System.out.println("Numeros: "+cNumeros);
        System.out.println("Correo: "+cMail);
        System.out.println("Signo: "+cSigno);
        System.out.println("Puntos: "+cPunto);
        System.out.println("Especial: "+cEspecial);
        for (int i = 0; i < largo; i++) {

            switch (correo.charAt(i)) {
                case '@':
                    arroba++;
                    break;
                case '.':
                    punto++;
                    break;
                default:
                    letra++;
                    break;
            }
//            System.out.println(correo.charAt(i));
//            System.out.println(arroba);
//            System.out.println(punto);
//            System.out.println(letra + "\n");
        }
//        int cLetras, cNumeros, cMail, cSigno, cPunto;
        if (arroba == 1 && punto != 0 && (cLetras!=0 || cNumeros!=0) && cMail!=0 && cSigno==0 && cPunto!=0 && cEspecial==0) {
            mai = true;
        }else{
            mai = false;
        }
        System.out.println("Boolean Mail: " + mai);
        return mai;
    }

    public boolean evaluaCoord(String lat, String lg) {
        int arrobaA = 0,
                puntoA = 0,
                letraA = 0,
                arrobaO = 0,
                puntoO = 0,
                letraO = 0,
                largoA = lat.length(),
                largoO = lg.length();
        evaluar(lat);
        System.out.println("-------Latitud");
        System.out.println("Letras: "+cLetras);
        System.out.println("Numeros: "+cNumeros);
        System.out.println("Correo: "+cMail);
        System.out.println("Signo: "+cSigno);
        System.out.println("Puntos: "+cPunto);
        System.out.println("Especial: "+cEspecial);

        evaluar(lg);
        System.out.println("---------Longitud");
        System.out.println("Letras: "+cLetras);
        System.out.println("Numeros: "+cNumeros);
        System.out.println("Correo: "+cMail);
        System.out.println("Signo: "+cSigno);
        System.out.println("Puntos: "+cPunto);
        System.out.println("Especial: "+cEspecial);
        for (int i = 0; i < largoA; i++) {

            switch (lat.charAt(i)) {
                case '@':
                    arrobaA++;
                    break;
                case '.':
                    puntoA++;
                    break;
                default:
                    letraA++;
                    break;
            }
        }

        for (int i = 0; i < largoO; i++) {
            switch (lg.charAt(i)) {
                case '@':
                    arrobaO++;
                    break;
                case '.':
                    puntoO++;
                    break;
                default:
                    letraO++;
                    break;
            }
//            System.out.println(correo.charAt(i));
//            System.out.println(arroba);
//            System.out.println(punto);
//            System.out.println(letra + "\n");
        }
//        int cLetras, cNumeros, cMail, cSigno, cPunto;
        if (puntoA == 1 && puntoO == 1  && cLetras==0 && cNumeros !=0 && (cMail==1 || cSigno !=0 || cPunto!=0) && cEspecial==0) {
            mai = true;
        }else{
            mai = false;
        }
        System.out.println("Boolean Coordenadas: " + mai);
        return mai;
    }

    // validating email id
    public boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
