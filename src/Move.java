
public class Move {
		
		private int currentPosition;
		private int destination;
		
		Move(int cur, int des){
			currentPosition = cur;
			destination = des;
			
		}
		public int getCurrent(){return currentPosition;}
		public int getDestination(){return destination;}
		
		public boolean check(Move move){
			
			if(this.currentPosition != move.getCurrent()){
				return false;
			}
			
			if(this.destination != move.getDestination()){
				return false;
			}
			
			return true;
		}
		
	}
	