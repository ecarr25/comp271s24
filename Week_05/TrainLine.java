public class TrainLine {
    
    private Station head;

    /**
     * Add a new station at the end of this trainline. The method creates
     * a new station object, first, with the given name. Then it checks to
     * if this line has a head station yet; if not, the new station becomes
     * the head station. If this line has a head station, the method begins
     * traversing this line from its head station, following the next points
     * untils it finds a station whose next is null. That station, by definition
     * is presently the last station in the line. The new station is added after 
     * that last station. 
     * 
     * @param name String with name of new station to add
     */
    public void addStation(String name) {
        // Create a new station object with the given name
        Station newStation = new Station(name);
        // Check if this trainline has a head station yet or not
        if (this.head == null) {
            // There is no head station in this trainline. Make the 
            // new station, just created, the head station and end.
            this.head = newStation;
        } else {
            // This line has a head station. Let's start traversing this line,
            // one station at a time, to find its last station. The station we
            // visit, in each step, is called the current station. We begin
            // with this trainline's head station.
            Station currentStation = this.head;
            // The while-loop below hops from station to station, until
            // it finds a station that points to null. That's the last
            // station of the line.
            while (currentStation.hasNext()) {
                currentStation = currentStation.getNext();
            }
            // When loop is over, currentStation is the last station. We want
            // to add the new station after the current station. All we have
            // to do is to make the current station point to the new station 
            // as its next station. Notice that the new station was created,
            // earlier, using the Station(String) constructor that left its
            // field next to null. Effectively, it is now the last station
            // this line because its field next is null.
            currentStation.setNext(newStation);
        }
    } // method addStation

    /**
     * see if trainline has a station with a certain name 
     * @param The name of the station to look for 
     * @return true if the station exists if not then false
     */

     public boolean contains(String name){
        return containsMatches(this.head, name);

     }// contains method

    /**
     * contains matches method helps find stations 
     * @param current station in the match 
     * @param name of the station to look for 
     * @return true if the station exists if not then false
     */

     private boolean containsMatches(Station current, string name){
        if (current == null){
            return false; // reached the end without finding a station 
        }
        if(current.getName().equals(name)){
            return true; //station found 
        }
        return containsMatches(current.getNext(), name);// move to the next station
     }// containsMatches

     
    /**
     *  makes a string of the train line with the station names in order
     *  @return a string of the train line
     */
     public String getTrainLineString() {
        StringBuilder sb = new StringBuilder();
        Station current = this.head;
        while (current != null) {
            sb.append(current.getName()); // append the name of the current station
            if (current.hasNext()) {
                sb.append(" -> "); // add a arrow if there is another station
            }
            current = current.getNext(); // go to the next station
        }
        return sb.toString(); //return to the string
     }
}
