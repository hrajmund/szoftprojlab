import java.util.ArrayList;

public class TranzisztorHandler {
    ArrayList<Tranzisztor> tranzisztorok;

    public void holderSet(Tranzisztor tranzisztor, Person person) {
        tranzisztor.setHolder(person);
        //kikeresi hogy van e olyan tranzisztor amelyik ugyanannál az embernél van és deaktivált, ha van akkor összekapcsolja őket a setConnected metódussal
    }
}
