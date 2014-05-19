package ec.gob.simintel.controladores.commons;

public interface MensajesError {
	/*
	 * Menajes comunes
	 */
	String COMMONS_INGRESAR_PARAMETRO_BUSQUEDA="Ingrese parametro a Buscar";
	String COMMONS_SELECCIONAR_CEDULAORUC = "Escoja una opcion de busqueda CEDULA o RUC  ";
	String COMMONS_SELECCIONAR_PARAMETRO_BUSQUEDA="Seleccione el parámetro de búsqueda";
	String COMMONS_TIENE_REGISTRO_ASOCIADOS="No se puede eliminar por que tiene registros asociados";
	String COMMONS_TIENE_REGISTRO_DUPLICADO="No se puede insertar por que el registro ya existe";
	String COMMONS_TIENE_REGISTRO_QUE_SE_PUEDE_ACTUALIZAR="No se puede actualizar el registro ya existe";
	String COMMONS_SELECCIONAR_REGISTRO="No se puede realizar cambios si no selecciona un registro";
	
	
	/*
	 * Seguridad
	 */
	String ERROR_PAGINA_NOMBRE_REPETIDO ="ERROR: Nombre de página ya existe";
	String ERROR_PAGINA_URL_REPETIDA ="ERROR: URL de página ya existe";
	String ERROR_PAGINA_CREADA ="ERROR: No se creo la página";
	String ERROR_PAGINA_EDITADA ="ERROR: No se actualizó la página";
	String ERROR_PAGINA_ELIMINADA ="ERROR: No se eliminó la página";
	
	String ERROR_ROL_NOMBRE_REPETIDO ="ERROR: Nombre del rol repetido";
	String ERROR_ROL_ELIMINADO ="ERROR: No se eliminó el rol.";
	String ERROR_ROL_SELECCION_REDIRECCION ="ERROR: Seleccion redirección";
	
	String LOGIN_ERROR ="ERROR: No existe usuario";
	String LOGIN_USR_SIN_PERFIL ="ERROR: Usuario no tiene ningún perfil";

	String NO_DIRECTORIO ="ERROR: El directorio incorrecto";
	
	String USUARIO_REPETIDO ="ERROR: Usuario ya existe";
	String ERROR_USUARIO_ELIMINADO ="ERROR: No se eliminó el usuario.";
	
	/*
	 * Mensajes de Institucion 
	 */
	String INSTITUCION_PARROQUIA_OBLIGATORIO="País- provincia -cantón - parroquia son campos obligatorios";
	String INSTITUCION_CONVENIO_OBLIGATORIO="Plan - programa - proyecto - convenio - inversion son campos obligatorios";
	String CONVENIO_ADMINISTRADOR_OBLIGATORIO="Escoger Administrador campo obligatorio";
	String CONVENIO_PROVEEDOR_OBLIGATORIO="Escoger Proveedor es campo obligatorio";
	String CONVENIO_TIPO_DOCUMENTO_OBLIGATORIO="Escoger Tipo Documento es campo obligatorio";
	String CONVENIO_ESTADO_OBLIGATORIO="Escoger Estado es campo obligatorio";
	
	
	/*
	 *Mensajes de Provincia 
	 */
	String PROVINCIA_PAIS_OBLIGATORIO="País es un campo obligatorio";
	String PARAMETROS_BUSQUEDA="Ingrese parametro a Buscar";
	String CANTON_PROVINCIA_OBLIGATORIO="Provincia es un campo obligatorio";
	String PARROQUIA_CANTON_OBLIGATORIO= "Canton es un campo obligatorios";
	String PROYECTO_PROGRAMA_OBLIGATORIO="Programa es un campo obligatorio";
	String CONVENIO_PROYECTO_OBLIGATORIO= "Proyecto es un campo obligatorio";
	String INVERSION_CONVENIO_OBLIGATORIO= "Convenio es un campo obligatorio";
	String INVERSION_TIPOINVERSION_OBLIGATORIO= "Tipo de Inversión es un campo obligatorio";
	String PROYECTO_PLAN_OBLIGATORIO="Plan es un campo obligatorio";
	
	/**
	 * Mensajes de Atributos Equipos
	 * 
	 */
	
	String ATRIBUTO_OBLIGATORIO="Debe escoger si es obligatorio";
	
	/*
	 *Mensajes de Contacto 
	 */
	String CONTACTO_DATOSAGENDA_OBLIGATORIOS="Medio es campo obligatorio";
}
