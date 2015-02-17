package hw1;

/**
 * Model of an awful Alarm Clock. 
 * More details about this assignment can be found: http://www.cs.iastate.edu/~cs227/homework/hw1/hw1.pdf
 * 
 * @author Gabby Ortman, *insert witty phrase here*. 
 *
 */

public class AlarmClock {
	
	/**
	 * Number of minutes to silence the alarm when snoozing.
	 */
	public static final int SNOOZE_MINUTES = 9;
	/**
	 * Number of minutes in a 24-hour day.
	 */
	public static final int MINUTES_PER_DAY = 1440;
	
	/**
	 * Holds the value of the time on the alarm clock.
	 */
	private int currentTime; 
	
	/**
	 * Holds the value of the clock's alarm time. 
	 */
	private int regularAlarm; 
	
	/**
	 * Boolean representing the alarm clock's awful ringing. 
	 */
	private boolean awfulRinging; 
	
	/**
	 * Hold the value of the clock's snooze alarm time. This only differs from regularAlarm when calling snooze(). 
	 */
	private int snoozeAlarm;  


	/**
	 * Constructs an alarm clock with initial clock time at 00:00 and alarm time at 01:00.
	 */
	public AlarmClock() {
		currentTime = 0;  
		regularAlarm = 60; 
		snoozeAlarm = regularAlarm; 
	}
	
	/** 
	 * Constructs an alarm clock with the given initial clock time and with alarm time at 01:00.
	 * @param hours- hour for clock time
	 * @param minutes- minutes for clock time
	 */
	public AlarmClock(int hours, int minutes) { 
		currentTime = (hours * 60) + minutes;  
		regularAlarm = 60; 
		snoozeAlarm = regularAlarm;
	}
	
	/**
	 * Advances the clock time by the specified number of minutes.
	 * @param minutes- the number of minutes to advance time
	 */
	public void advanceTime(int minutes) { 			
		currentTime = currentTime + minutes; 
		
		//Conditional statement to have alarm start ringing. 
		//used snoozeAlarm because it equals regularAlarm everywhere except when snoozing. 
		if (currentTime >= snoozeAlarm) { 
			awfulRinging = true; 
		}
	}
	
	/**
	 * Advances the clock time by the given hours and minutes.
	 * @param hours- number of hours to advance the time
	 * @param minutes- the number of minutes to advanece time
	 */
	public void advanceTime(int hours, int minutes)	{ 	
		minutes = hours * 60 + minutes; 
		advanceTime(minutes);  

	}
	
	/**
	 * Turns off the alarm, stops it from ringing (if it is ringing) and cancels snooze if it is in effect.
	 */
	//Alarm off *clap clap* 
	public void alarmOff() {		//TODO 
		awfulRinging = false; 
		snoozeAlarm = regularAlarm; 
	}
	
	/**
	 * Turns the alarm on.   
	 */
	//Alarm on *clap clap* 
	//Also, https://www.youtube.com/watch?v=f_SwD7RveNE 
	public void alarmOn() {		//TODO
		snoozeAlarm = regularAlarm;  
	}
	
	/**
	 * Returns the current alarm time as the number of minutes past midnight.
	 * @return- number of minutes past midnight for the alarm time 
	 */
	public int getAlarmTime()	{
		return regularAlarm = regularAlarm % MINUTES_PER_DAY; 
	}
	
	/** 
	 * Returns the current alarm time as a string of the form hh:mm.
	 * @return- alarm time in string form
	 */
	public String getAlarmTimeAsString() {
		int hours = getAlarmTime() / 60; 
		int minutes = getAlarmTime() % 60; 
		String timeString = String.format("%02d:%02d", hours, minutes); 
		return timeString; 
		 
	}
	
	/** 
	 * Returns the current clock time as the number of minutes past midnight.
	 * @return- number of minutes past midnight for clock time 
	 */
	public int getClockTime()	{
		return currentTime = currentTime % MINUTES_PER_DAY;  
	}
	
	/**
	 * Returns the current clock time as a string of the form hh:mm.
	 * @return- clock time in string form (hour:minutes)
	 */
	public String getClockTimeAsString() {	
		int hours = getClockTime() / 60; 
		int minutes = getClockTime() % 60; 
		String timeString = String.format("%02d:%02d", hours, minutes); //Thanks, Steve. 
		return timeString; 
	}
	
	/**
	 * Returns the effective alarm time as the number of minutes past midnight.
	 * @return- number of minutes past midnight for the effective alarm time
	 */
	public int getEffectiveAlarmTime()	{
		return snoozeAlarm = snoozeAlarm % MINUTES_PER_DAY; 
	}
	
	/**
	 * Returns the effective alarm time as a string of the form hh:mm.
	 * @return- effective alarm time in string form 
	 */
	public String getEffectiveAlarmTimeAsString() { 
		int hours = getEffectiveAlarmTime() / 60; 
		int minutes = getEffectiveAlarmTime() % 60; 
		String timeString = String.format("%02d:%02d", hours, minutes);  
		return timeString; 
	}
	
	/**
	 * Determines whether the clock is currently ringing.
	 * @return- returns true if the clock is currently ringing
	 */
	public boolean isRinging() { 
		return awfulRinging; 
	}
	
	/**
	 * Sets the alarm time to the given hours and minutes.
	 * @param hours- hour for alarm time 
	 * @param minutes- minutes for alarm time 
	 */
	public void setAlarmTime(int hours, int minutes) { 
		regularAlarm = (hours * 60) + minutes; 
		snoozeAlarm = regularAlarm;
		
		//If the clock is set and the alarm are set at the same time, the alarm will not ring for another 24 hours. 
		if (currentTime == regularAlarm) { 
			regularAlarm = regularAlarm + MINUTES_PER_DAY; 
			snoozeAlarm = regularAlarm; 
		}
	}
	
	/**
	 * Sets the current clock time to the given hours and minutes.
	 * @param hours- hour for alarm time 
	 * @param minutes- minutes for alarm time 
	 */
	public void setTime(int hours, int minutes)	{ 
		currentTime = (hours * 60) + minutes;   
		snoozeAlarm = regularAlarm;
		
	}
	
	/**
	 * Stops the clock from ringing and sets the effective alarm time for SNOOZE_MINUTES minutes after the current clock time.
	 */
	public void snooze() { 
		if (awfulRinging){ 
			awfulRinging = false; 
			snoozeAlarm = currentTime + SNOOZE_MINUTES; 
		}
	}
}
