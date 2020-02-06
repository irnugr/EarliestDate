package Find.EarliestDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App 
{
    public static void main( String[] args ) throws ParseException
    {
        String s = 
        		"admin  -wx 29 Sep 1983        833 source.h\r\n" + 
        		"admin  r-x 23 Jun 2003     854016 blockbuster.mpeg\r\n" + 
        		"admin  --x 02 Jul 1988        821 delete-this.py\r\n" + 
        		"admin  -w- 15 Feb 1971      23552 library.dll\r\n" + 
        		"admin  --x 15 May 1979  645922816 logs.zip\r\n" + 
        		"jane   --x 04 Dec 1922      93184 old-photos.rar\r\n" + 
        		"jane   -w- 08 Feb 1982  681574400 important.java\r\n" + 
        		"admin  rwx 26 Dec 1952   14680064 to-do-list.txt";
        List<myList> findTheEarliest = new ArrayList<myList>();
        findTheEarliest = findEarliestDate(s);
        
        if (findTheEarliest.size() > 0) {
        	for (int index=0;index<findTheEarliest.size();index++) {
        		System.out.println(findTheEarliest.get(index).getOwner()+" "+
        				findTheEarliest.get(index).getPerm()+" "+
        				findTheEarliest.get(index).getListDate()+" "+
        				findTheEarliest.get(index).getSize()+" "+
        				findTheEarliest.get(index).getFileName()
        				);
        	}
        }
    }   
    
    public static List<myList> findEarliestDate(String S) throws ParseException {
    	
    	String[] values = S.split("\r\n");
    	List<myList> myList = new ArrayList<myList>();
    	List<myList> earliestList = new ArrayList<myList>();
    	SortedSet<Date> dates = new TreeSet<Date>();
    	
    	for (int i=0;i<values.length;i++) {
    		
    		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
    		myList itsTheList = new myList();
    		Date date2=formatter2.parse(values[i].substring(11, 13)+"-"+values[i].substring(14, 17)+"-"+values[i].substring(18,22));
    		
        	itsTheList.setOwner(values[i].substring(0,6).trim());
        	itsTheList.setPerm(values[i].substring(7, 10).trim());
        	itsTheList.setListDate(date2);
        	itsTheList.setSize(Integer.parseInt(values[i].substring(23, 33).trim()));
        	itsTheList.setFileName(values[i].substring(34, values[i].length()).trim());
        	
        	if (itsTheList.getOwner().equals("admin") && itsTheList.getPerm().indexOf('x') > 0) {
        		dates.add(date2);
        		myList.add(itsTheList);
        	}
        }
    	
    	Date earliest = Collections.min(dates);

    	for (int index=0;index<myList.size();index++) {
    		myList itsTheList = new myList();
    		itsTheList.setOwner(myList.get(index).getOwner());
    		itsTheList.setPerm(myList.get(index).getPerm());
    		itsTheList.setListDate(myList.get(index).getListDate());
    		itsTheList.setSize(myList.get(index).getSize());
    		itsTheList.setFileName(myList.get(index).getFileName());
    		if (myList.get(index).getListDate().equals(earliest)) {
    			earliestList.add(itsTheList);
    		}
    	}
    	return earliestList;
    }

}

class myList {
	
	String owner;
	String perm;
	Date listDate;
	int size;
	String fileName;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getPerm() {
		return perm;
	}
	public void setPerm(String perm) {
		this.perm = perm;
	}
	public Date getListDate() {
		return listDate;
	}
	public void setListDate(Date listDate) {
		this.listDate = listDate;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}