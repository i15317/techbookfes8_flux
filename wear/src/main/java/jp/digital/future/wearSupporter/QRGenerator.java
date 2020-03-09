package jp.digital.future.wearSupporter;

import android.graphics.Bitmap;
import android.util.AndroidRuntimeException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;

public class QRGenerator {
    public Bitmap makeBitmap(String data) {
        //QRコード画像の大きさを指定(pixel)
        int size = 150;

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            HashMap hints = new HashMap();

            //文字コードの指定
            hints.put(EncodeHintType.CHARACTER_SET, "shiftjis");

            //誤り訂正レベルを指定
            //L 7%が復元可能
            //M 15%が復元可能
            //Q 25%が復元可能
            //H 30%が復元可能
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

            //QRコードのバージョンを指定
            hints.put(EncodeHintType.QR_VERSION, 8);

            Bitmap bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, size, size, hints);
            return bitmap;
        } catch (WriterException e) {
            throw new AndroidRuntimeException("Barcode Error.", e);
        }
    }
}
