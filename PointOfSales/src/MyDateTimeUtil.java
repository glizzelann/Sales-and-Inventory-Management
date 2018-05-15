import java.sql.Timestamp;

public class MyDateTimeUtil {

	
		public static boolean isRevokedTimeLessThan24Hrs(String timestamp){
	        Timestamp timeRevoked = new Timestamp(Long.valueOf(timestamp));
	        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
	        
	        long diff = timeNow.getTime() - timeRevoked.getTime();
	        long diffHours = diff / (60 * 60 * 1000);
	        
	        if (diffHours>=24)
	            return false;
	        
	        return true;
	    }
	    
	    public static boolean isRevokedTimeLessThan30Mins(String timestamp){
	        if (timestamp==null || timestamp.isEmpty())
	            return false;
	      
	        
	        Timestamp timeRevoked = new Timestamp(Long.valueOf(timestamp));
	        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
	        
	        long diff = timeNow.getTime() - timeRevoked.getTime();
	        long diffMinutes = diff / (60 * 1000);
	        System.out.println("diffMinutes = "+diffMinutes);
	        if (diffMinutes>=1)
	            return false;
	        
	        return true;
	    }
	   
	
	}


