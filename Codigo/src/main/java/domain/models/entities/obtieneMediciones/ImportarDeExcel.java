package domain.models.entities.obtieneMediciones;

import domain.models.entities.consumo.*;
import domain.models.entities.consumo.*;
import domain.models.entities.organizacion.Organizacion;
import domain.services.dbManager.EntityManagerHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
public class ImportarDeExcel {
    public void importar(String nombreExcel, Organizacion unaOrganizacion) throws IOException {
            File file = new File(".\\ddsExcelv2.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            Row row = itr.next();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            Boolean esMensual = false;
            Boolean esAnual = false;
            Boolean esLogistica = false;

            List<String> datosFila = new ArrayList<>();
            List<FilaConsumo> filaDeConsumos = new ArrayList<>();
            while (itr.hasNext()) {
                row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:    //field that represents string cell type
                            String leido = cell.getStringCellValue();
                            if (leido == "LOGISTICA_PRODUCTOS_RESIDUOS"){
                                esLogistica = true;
                            }
                            datosFila.add(leido);
                            //System.out.print(leido + "\t\t\t");
                            if (Objects.equals(leido.toLowerCase(), "mensual")) {
                                esMensual = true;
                            }
                            if (Objects.equals(leido.toLowerCase(), "anual")) {
                                esAnual = true;
                            }

                            break;
                        case NUMERIC:    //field that represents number cell type
                            int leido2 = (int) cell.getNumericCellValue();
                            String leidoString = String.valueOf(leido2);
                            if (esAnual) {
                                //System.out.print(leido2 + "\t\t\t");
                                datosFila.add(leidoString);
                                esAnual = false;
                            } else if (esMensual) {
                                String leidoFecha = sdf.format(cell.getDateCellValue());
                                //System.out.print(leidoFecha + "\t\t\t");
                                esMensual = false;
                                datosFila.add(leidoFecha);
                            } else {
                                //System.out.print(cell.getNumericCellValue() + "\t\t\t");
                                datosFila.add(leidoString);
                            }

                            break;
                        default:
                            break;
                    }
                    //AÑADIR VALOR AL ARRAYLIST

                }
                //SE INSTANCIA LA CLASE FILACONSUMO
                System.out.println(datosFila);
                FilaConsumo filaConsumoInstancia = new FilaConsumo();
                //Agrega datos Fila a una instancia de FilaConsumo
                filaConsumoInstancia.setDatosString(datosFila);
                filaDeConsumos.add(filaConsumoInstancia);
                //SE VACIA EL ARRAYLIST
                datosFila.clear();
                System.out.println("");
            }
            instanciasConsumosParaOrganizacion(filaDeConsumos,unaOrganizacion);
    }

    public void instanciasConsumosParaOrganizacion(List<FilaConsumo> listaDeFilaConsumo, Organizacion organizacion) {
        for (int i = 0; i < listaDeFilaConsumo.size() ; i++) {
            FilaConsumo fila = listaDeFilaConsumo.get(i);
            if (fila.getDatosString().get(1).toLowerCase(Locale.ROOT).contains("categoria") ){
                FilaConsumo medio = listaDeFilaConsumo.get(i+1);
                FilaConsumo  distancia= listaDeFilaConsumo.get(i+2);
                FilaConsumo  peso= listaDeFilaConsumo.get(i+3);
                instaciarConsumoLogistica(fila, medio, distancia, peso,organizacion); // por fila se refiere a categoria
                i += 3; // te saltea las proximas tres filas de una
            } else {
                organizacion.agregarConsumo(instanciarOtroConsumo(listaDeFilaConsumo.get(i)));
            }
        }
    }

    public Consumo instaciarConsumoLogistica(FilaConsumo categoria, FilaConsumo medio, FilaConsumo distancia, FilaConsumo peso, Organizacion unaOrganizacion){
        List<String> df = categoria.getDatosString();
        Actividad actividadConsumo = new Actividad(tipoDeAlcanceConsumo(df.get(0)), df.get(0));
        TipoConsumo tipoConsumo = new TipoConsumo(df.get(1), unidadPorConsumo(df.get(1)));
        PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(df.get(4));
        Consumo consumoDeOrganizacion = new ConsumoLogistica(actividadConsumo, periodoDeImputacion, tipoConsumo, Integer.parseInt(peso.getDatosString().get(2)),
                Integer.parseInt(distancia.getDatosString().get(2)), medio.getDatosString().get(2), categoria.getDatosString().get(2));
        unaOrganizacion.agregarConsumo(consumoDeOrganizacion);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(unaOrganizacion);
        EntityManagerHelper.persist(actividadConsumo);
        EntityManagerHelper.persist(tipoConsumo);
        EntityManagerHelper.persist(periodoDeImputacion);
        EntityManagerHelper.persist(consumoDeOrganizacion);
        EntityManagerHelper.commit();
        return consumoDeOrganizacion;
    }


    public Consumo instanciarOtroConsumo(FilaConsumo filaConsumo) {
        List<String> df = filaConsumo.getDatosString();
        Actividad actividadConsumo = new Actividad(tipoDeAlcanceConsumo(df.get(0)), df.get(0));
        TipoConsumo tipoConsumo = new TipoConsumo(df.get(1), unidadPorConsumo(df.get(1)));
        PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(df.get(4));
        Consumo consumoDeOrganizacion = new OtroConsumo(actividadConsumo, periodoDeImputacion, tipoConsumo, Double.parseDouble(df.get(2)));
        return consumoDeOrganizacion;
    }

    public TipoAlcance tipoDeAlcanceConsumo(String actividad) {
        if (actividad.equalsIgnoreCase("Electricidad adquirida y consumida")) {
            return TipoAlcance.EMISIONESINDIRECTASELECTRICIDAD;
        } else if (actividad.equalsIgnoreCase("Logística de productos y residuos")) {
            return TipoAlcance.EMISIONESINDIRECTASNOCONTROLADAS;
        } else {
            return TipoAlcance.EMISIONESDIRECTAS;
        }
    }

    public Unidad unidadPorConsumo(String consumo) {
        if (consumo.equalsIgnoreCase("gas natural")) {
            return Unidad.M3;
        } else if (consumo.matches("(?i)diesel/gasoil|kerosene|fuel oil|nafta")) {
            return Unidad.LT;
        } else if (consumo.matches("(?i)carbon|carbon de leña|leña|peso total transportado")) {
            return Unidad.KG;
        } else if (consumo.toLowerCase().contains("combustible consumido")) {
            return Unidad.LTS;
        } else if (consumo.equalsIgnoreCase("electricidad")) {
            return Unidad.KWH;
        } else if (consumo.equalsIgnoreCase("distancia media recorrida")) {
            return Unidad.KM;
        }
        return null;
    }
}

