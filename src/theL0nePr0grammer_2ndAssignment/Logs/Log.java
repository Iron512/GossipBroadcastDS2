package theL0nePr0grammer_2ndAssignment.Logs;

import java.util.ArrayList;
import java.util.List;

import theL0nePr0grammer_2ndAssignment.Relays.Relay;

public class Log {
	private Relay id;
	List<Event> trace;
	
	public Log(Relay id) {
		this.id = id;
		this.trace = new ArrayList<Event>();
	}
	
	public void append(Object content) {
		if (this.last() != null) {
			String hash = this.last().getHash();
			this.trace.add(new Event(this.id, hash, this.last().getNextIndex(),content));
		}
		else
			this.trace.add(new Event(this.id,null,0,content));
	}
	
	public Event last() {
		if (this.trace.size() > 0)
			return this.trace.get(this.trace.size()-1);
		else 
			return null;
	}
	
	public Relay getId() {
		return this.id;
	}
	
	public void update(Log foreign) {
		if (this.id == foreign.id) {
			foreign.trace.forEach(e -> {
				if ((this.last() == null && e.getIndex() == 0) || (this.last().getHash().equals(e.getPrevious())))
					this.trace.add(e);
			});
		}
	}
}
