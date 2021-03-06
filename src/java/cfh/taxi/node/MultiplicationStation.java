package cfh.taxi.node;

import static cfh.taxi.node.NodeClass.NUMERIC;

import java.util.Deque;
import java.util.EnumSet;

import cfh.taxi.Location;
import cfh.taxi.Passenger;
import cfh.taxi.TaxiException;
import cfh.taxi.Program.InputOutput;

public class MultiplicationStation extends Location {

    MultiplicationStation(NodeType type, String name, int x, int y) {
        super(type, name, x, y);
    }
    
    @Override
    public String description() {
        return "multiplies numerical passengers, anything non-numeric is an error";
    }
    
    @Override
    public EnumSet<NodeClass> nodeClass() {
        return EnumSet.of(NUMERIC);
    }
    
    @Override
    protected void receive(Deque<Passenger> incoming, InputOutput inpout) throws TaxiException {
        if (incoming.isEmpty())
            return;
        double value = 1.0;
        while (!incoming.isEmpty()) {
            Passenger passenger = incoming.removeFirst();
            value *= passenger.value.number(this);
        }
        addOutgoing(new Passenger(value));
    }
}
