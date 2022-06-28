package domain.obtieneMediciones;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.sun.org.apache.xpath.internal.operations.Or;
import domain.consumo.*;
import domain.organizacion.Organizacion;
import domain.consumo.TipoAlcance;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ImportarDeExcel {
    public static void main(String[] args) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void instanciasConsumosParaOrganizacion(List<FilaConsumo> listaDeFilaConsumo, Organizacion organizacion) {
        for (FilaConsumo filaConsumo : listaDeFilaConsumo) {
            organizacion.agregarConsumo(instanciarFilaConsumo(filaConsumo));
        }
    }

    public Consumo instanciarFilaConsumo(FilaConsumo filaConsumo) {
        List<String> df = filaConsumo.getDatosString();
        Actividad actividadConsumo = new Actividad(tipoDeAlcanceConsumo(df.get(0)), df.get(0));
        TipoConsumo tipoConsumo = new TipoConsumo(df.get(1), unidadPorConsumo(df.get(1)));
        PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(df.get(4));
        Consumo consumoDeOrganizacion = new Consumo(actividadConsumo, periodoDeImputacion, tipoConsumo, Double.parseDouble(df.get(2)));
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

