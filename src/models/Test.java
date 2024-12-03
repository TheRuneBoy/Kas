//package models;
//
//import java.time.LocalDate;
//
//public class Test {
//    public static void main(String[] args) {
//        Konference konference = new Konference("Hav og himmel",200,"EAAA", LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),1500);
//        Hotel hotel1 = new Hotel("Hvide svane","hvidesvane gade", 1050,1250);
//        HotelTilæg hotelTilæg1 = new HotelTilæg("wifi",50);
//        Udflugt udflugt1 = new Udflugt("Egeskov",LocalDate.of(2024,12,16),75);
//        Udflugt udflugt2 = new Udflugt("Trapholt",LocalDate.of(2024,12,17),200);
//        Udflugt udflugt3 = new Udflugt("Byrundtur Odense",LocalDate.of(2024,12,18),125);
//        Ledsager ledsager1 = new Ledsager("mie");
//
////        Deltager deltager1 = new Deltager("Finn madsen", "blabla","ewewewew",null);
////        Tilmelding tilmeldingFinn = new Tilmelding(null,false,LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),deltager1,konference,null);
////        Deltager deltager2 = new Deltager("Niels Peter","fjsba","vdbj",null);
////        Tilmelding tilmeldingNiels = new Tilmelding(hotel1,false,LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),deltager2,konference,null);
////        Deltager deltager3 = new Deltager("peter sommer","d","fs",null);
////        Tilmelding tilmeldingPeter = new Tilmelding(hotel1,false,LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),deltager3,konference,ledsager1);
//
//        tilmeldingPeter.addUdflugt(udflugt1);
//        tilmeldingPeter.addUdflugt(udflugt2);
//        tilmeldingPeter.addhotelTilæg(hotelTilæg1);
//
//        Deltager deltager4 = new Deltager("Lone Jensen","wee","ewew",null);
//        Tilmelding tilmeldingLone = new Tilmelding(hotel1,true,LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),deltager4,konference,ledsager1);
//        tilmeldingLone.addhotelTilæg(hotelTilæg1);
//        tilmeldingLone.addUdflugt(udflugt1);
//        tilmeldingLone.addUdflugt(udflugt3);
//
//
//        System.out.println("lone");
//        System.out.println("Number of days: " + tilmeldingLone.antaldage);
//
//        System.out.println("Calculating prices...");
//        System.out.println("Conference Price: " + tilmeldingLone.beregnKonferencePris());
//        System.out.println("Hotel Price: " + tilmeldingLone.beregnHotelPris());
//        System.out.println("Hotel Tilkøb Price: " + tilmeldingLone.beregnHotelTilkøbPris());
//        System.out.println("Udflugt Price: " + tilmeldingLone.beregnUdflgtsPris());
//        System.out.println("Total Price: " + tilmeldingLone.BeregnTotalPris());
//
//        System.out.println("Peter/////////////////////////////////////////////////////");
//        System.out.println("Number of days: " + tilmeldingPeter.antaldage);
//
//        System.out.println("Calculating prices...");
//        System.out.println("Conference Price: " + tilmeldingPeter.beregnKonferencePris());
//        System.out.println("Hotel Price: " + tilmeldingPeter.beregnHotelPris());
//        System.out.println("Hotel Tilkøb Price: " + tilmeldingPeter.beregnHotelTilkøbPris());
//        System.out.println("Udflugt Price: " + tilmeldingPeter.beregnUdflgtsPris());
//        System.out.println("Total Price: " + tilmeldingPeter.BeregnTotalPris());
//
//        System.out.println("NIels//////////////////////////////////////////////");
//        System.out.println("Number of days: " + tilmeldingNiels.antaldage);
//
//        System.out.println("Calculating prices...");
//        System.out.println("Conference Price: " + tilmeldingNiels.beregnKonferencePris());
//        System.out.println("Hotel Price: " + tilmeldingNiels.beregnHotelPris());
//        System.out.println("Hotel Tilkøb Price: " + tilmeldingNiels.beregnHotelTilkøbPris());
//        System.out.println("Udflugt Price: " + tilmeldingNiels.beregnUdflgtsPris());
//        System.out.println("Total Price: " + tilmeldingNiels.BeregnTotalPris());
//
//        System.out.println("FINN/////////////////////////////////////////////////////////////");
//        System.out.println("Number of days: " + tilmeldingFinn.antaldage);
//
//        System.out.println("Calculating prices...");
//        System.out.println("Conference Price: " + tilmeldingFinn.beregnKonferencePris());
//        System.out.println("Hotel Price: " + tilmeldingFinn.beregnHotelPris());
//        System.out.println("Hotel Tilkøb Price: " + tilmeldingFinn.beregnHotelTilkøbPris());
//        System.out.println("Udflugt Price: " + tilmeldingFinn.beregnUdflgtsPris());
//        System.out.println("Total Price: " + tilmeldingFinn.BeregnTotalPris());
//
//
//
//
//    }
//}
