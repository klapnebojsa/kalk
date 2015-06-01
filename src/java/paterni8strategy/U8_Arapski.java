/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni8strategy;

/**
 *
 * @author neso
 */
public class U8_Arapski extends U8_UPUTSTVO { //ConcreteStrategyC
    //1, Familija algoritama    
    String Opis(){
        String opis="";
        opis += "STRATEGY: استراتيجية: استعراض الفرق في السعر يحصل نظرة ثاقبة على المعلومات التالية:";
        opis += "عدد الحسابات - الحسابات رقم الداخلية.";
        opis += "التاريخ - تاريخ الشراء.";
        opis += "انزلاق - شكلت الفرق في السعر لحساب المذكور.";
        return opis;
    } 
}
