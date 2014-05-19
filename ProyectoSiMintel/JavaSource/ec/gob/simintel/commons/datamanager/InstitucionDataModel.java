package ec.gob.simintel.commons.datamanager;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import ec.gob.simintel.entidades.Institucion;


public class InstitucionDataModel extends ListDataModel<Institucion> implements SelectableDataModel<Institucion> {
	public InstitucionDataModel (List<Institucion> lstInstituciones){
		super(lstInstituciones);
	}
	
	
	@Override
	public Institucion getRowData(String rowkey) {
		// TODO Auto-generated method stub
				
		List<Institucion> instituciones = (List<Institucion>) getWrappedData();
		System.out.println("Entro a getRowData/Imprimir rowKey:" + rowkey);
		for(Institucion inst : instituciones) {
			if(inst.getInstitucionid().toString().compareTo(rowkey.toString())==0){				
				return inst;
			}
			
		}
 		return new Institucion();
	}

	@Override
	public Object getRowKey(Institucion institucion) {
		// TODO Auto-generated method stub
		return institucion.getInstitucionid();
	}

}
