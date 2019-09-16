package top.hting.setup;

import cn.afterturn.easypoi.word.WordExportUtil;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme;
import static org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme.INT_MINOR_ASCII;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.DateUtils;
import top.hting.entity.oracle.setup.SetupPriItem;
import top.hting.entity.oracle.setup.SetupPrivateInfoVO;
import top.hting.mapper.oracle.setup.TblSetupPrivateMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLicenseSetup {

    @Autowired
    TblSetupPrivateMapper tblSetupPrivateMapper;

    @Test
    public void pdfExport() throws IOException {
        XWPFDocument document = new XWPFDocument(new FileInputStream(new File("C:\\Users\\NC040\\Desktop\\export\\专用航标设置审批结果技术数据一览表.docx")));
        for (XWPFTable table : document.getTables()) {

            for (XWPFTableRow row : table.getRows()) {

                for (XWPFTableCell tableCell : row.getTableCells()) {

                    System.out.println("单元格段落数" + tableCell.getParagraphs().size());
                    List<XWPFParagraph> paragraphs = tableCell.getParagraphs();
                    if (paragraphs.size()>0 && paragraphs.get(0).getRuns().size()>0) {

                        CTFonts rFonts = tableCell.getParagraphs().get(0).getRuns().get(0).getCTR().getRPr().getRFonts();

                        if (rFonts != null) {
                            //<w:rFonts w:hAnsi="宋体" w:eastAsia="宋体" w:ascii="宋体" w:hint="eastAsia"/>
                            rFonts.setAscii("宋体");
                            rFonts.setHAnsi("宋体");
                            rFonts.setEastAsia("宋体");
                            rFonts.setCs("宋体");

                            rFonts.setEastAsiaTheme(STTheme.Enum.forInt(INT_MINOR_ASCII));
                            rFonts.setCstheme(STTheme.Enum.forInt(INT_MINOR_ASCII));
                            rFonts.setAsciiTheme(STTheme.Enum.forInt(INT_MINOR_ASCII));
                            rFonts.setHAnsiTheme(STTheme.Enum.forInt(INT_MINOR_ASCII));

                            tableCell.getParagraphs().get(0).getRuns().get(0).getCTR().getRPr().setRFonts(rFonts);
                            System.out.println(tableCell.getCTTc().toString());
                        }
                    }

                }
                // System.out.println(row.getCtRow().toString());

            }


        }
        document.write(new FileOutputStream(new File("d:\\tmp.docx")));

        FileOutputStream target = new FileOutputStream(new File("d:\\2.pdf"));

        PdfOptions options = PdfOptions.create();
        PdfConverter.getInstance().convert(document, target, options);


    }

    @Test
    public void export() {
        String setupId = "c6b333b6-65d2-4399-a72e-eaf4e8f5f658";

        SetupPrivateInfoVO setupPrivateInfoVO = tblSetupPrivateMapper.selectInfoById(setupId);

        List<SetupPriItem> setupPriItems = tblSetupPrivateMapper.selectBySetupId(setupId);

        Map<String, Object> params = new HashMap<>();

        // 主数据填充
        params.put("licenseNumber", setupPrivateInfoVO.getLicenseNumber());
        params.put("applyOrgName", setupPrivateInfoVO.getApplyOrgName());
        params.put("applyTime", DateUtils.formatDateNotTime(setupPrivateInfoVO.getApplyTime()));
        params.put("acceptDate", DateUtils.formatDateNotTime(setupPrivateInfoVO.getAcceptDate()));
        params.put("copyOrg", setupPrivateInfoVO.getCopyOrg());
        params.put("approveDate", DateUtils.formatDateNotTime(setupPrivateInfoVO.getApproveDate()));
        // 许可项数据 TODO
        // 先拼接一段数据

        StringBuilder builder = new StringBuilder();

        for (SetupPriItem item : setupPriItems) {
            builder.append("   ").append(item.getSerialNumber()).append("、").append(item.getItemContent()).append("\r\n");
        }
        params.put("content", builder.toString());

        String templdatePath = "C:\\Users\\NC040\\Desktop\\license.docx";
        try {
            XWPFDocument document = WordExportUtil.exportWord07(templdatePath, params);

            // FileOutputStream outputStream = new FileOutputStream(new File("d:\\1.docx"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


            document.write(outputStream);
            outputStream.close();

            FileOutputStream target = new FileOutputStream(new File("d:\\1.pdf"));

            // XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(outputStream.toByteArray()));
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, target, options);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
