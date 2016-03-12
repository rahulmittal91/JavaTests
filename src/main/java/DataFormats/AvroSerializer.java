package DataFormats;

import com.intuit.blink.replicator.common.exceptions.EventDecoderException;
import com.intuit.blink.replicator.common.exceptions.EventEncoderException;
import com.intuit.blink.replicator.common.serde.AvroEventDecoder;
import com.intuit.blink.replicator.common.serde.AvroEventEncoder;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by rmittal1 on 3/2/16.
 */
public class AvroSerializer {

    String stringSchema = "{\"type\": \"record\", \n" + " \"name\": \"test\",\n" + " \"fields\" : [\n"
        + " {\"name\": \"a\",\"type\": [\"null\" ,\"string\"],\n" + "  \"logicalType\": \"decimal\",\n"
        + "  \"precision\": 30,\n" + "  \"scale\": 2\n" + "},\n" + " {\"name\": \"b\", \"type\": \"string\"},\n"
        + "{\"name\" : \"c\" , \"type\": \"string\",\n" + "  \"logicalType\": \"decimal\",\n" + "  \"precision\": 38,\n"
        + "  \"scale\": 0\n" + "}\n" + "]}";
    Schema schema = Schema.parse(stringSchema);
    GenericRecord gr = new GenericData.Record(schema);
    GenericRecord gr1 = new GenericData.Record(schema);
    GenericRecord gr2 = new GenericData.Record(schema);
    GenericRecord gr3 = new GenericData.Record(schema);

    private void createRecord(){
        String s = "100000000000000000000000000";
        BigInteger b = new BigInteger(s);
        gr.put("a" , "100.2");
        gr.put("b", "chandra");
        gr.put("c" , s);

        String s1 = "55555555555555555555555555555";
        BigInteger b1 = new BigInteger(s1);
        //gr1.put("a" , ByteBuffer.wrap(b1.toByteArray()));

        gr2.put("a" , null);
        gr2.put("b" , "kamesh");
        gr2.put("c" , s1);

        gr3.put("a" , s);
        gr3.put("b" , "rahul");
        gr3.put("c" , "100.2");
    }
    private void serialize() throws EventEncoderException, EventDecoderException {
        AvroEventEncoder ka = new AvroEventEncoder(schema);
        byte[] b =ka.toBytes(gr);
        AvroEventDecoder decoder = new AvroEventDecoder(schema);
        GenericRecord genericRecord = decoder.fromBytes(b);
    }

    private void serialize1() throws IOException {
        File file = new File("/tmp/users.avro");
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(gr);
        dataFileWriter.append(gr2);
        dataFileWriter.append(gr3);
        dataFileWriter.close();
    }
    public static void main(String args[]) throws EventEncoderException,EventDecoderException, IOException {
        AvroSerializer avroDataTypes = new AvroSerializer();
        avroDataTypes.createRecord();
        System.out.println(avroDataTypes.gr.toString());
        avroDataTypes.serialize1();
        List<Schema.Field> fields = avroDataTypes.schema.getFields();
        for(Schema.Field field : fields){
            System.out.println(field.name() + field.getJsonProps().toString());
        }
    }

}
