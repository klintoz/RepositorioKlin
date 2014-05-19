package ec.gob.simintel.commons.datamanager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ec.gob.simintel.entidades.Institucion;

@ManagedBean
public class LazyInstitucionDataModel  extends LazyDataModel<Institucion>{
	
	private List<Institucion> dataInstitucion;
	
	public LazyInstitucionDataModel (List<Institucion> dataInstitucion){
		this.dataInstitucion =dataInstitucion;
	}
	
	 @Override  
	    public Institucion getRowData(String identificacion) {  
	        for(Institucion institucion : dataInstitucion) {  
	            if(institucion.getIdentificacion().equals(identificacion))  
	                return institucion;  
	        }  
	  
	        return null;  
	    }  
	  
	    @Override  
	    public Object getRowKey(Institucion institucion) {  
	        return institucion.getIdentificacion();  
	    }  
	  
	    @Override  
	    public List<Institucion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
	        List<Institucion> institucion = new ArrayList<Institucion>();  
	  
	        //filter  
	        for(Institucion inst : dataInstitucion) {  
	            boolean match = true;  
	  
	            for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {  
	                try {  
	                    String filterProperty = it.next();  
	                    String filterValue = filters.get(filterProperty);  
	                    String fieldValue = String.valueOf(inst.getClass().getField(filterProperty).get(inst));  
	  
	                    if(filterValue == null || fieldValue.startsWith(filterValue)) {  
	                        match = true;  
	                    }  
	                    else {  
	                        match = false;  
	                        break;  
	                    }  
	                } catch(Exception e) {  
	                    match = false;  
	                }   
	            }  
	  
	            if(match) {  
	                institucion.add(inst);  
	            }  
	        }  
	  
	        //sort  
	        //if(sortField != null) {  
	          //  Collections.sort(institucion, new LazySorter(sortField, sortOrder));  
	       // }  
	  
	        //rowCount  
	        int dataSize = institucion.size();  
	        this.setRowCount(dataSize);  
	  
	        //paginate  
	        if(dataSize > pageSize) {  
	            try {  
	                return institucion.subList(first, first + pageSize);  
	            }  
	            catch(IndexOutOfBoundsException e) {  
	                return institucion.subList(first, first + (dataSize % pageSize));  
	            }  
	        }  
	        else {  
	            return institucion;  
	        }  
	    }  
}
		
	
	

	
