package DataFormats;

import com.intuit.blink.replicator.common.exceptions.EventDecoderException;
import com.intuit.blink.replicator.common.exceptions.EventEncoderException;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by rmittal1 on 3/2/16.
 */
public class AvrOserializerAgain {

    String stringSchema =
        "   {\n" + "     \"type\": \"record\", \n" + "     \"name\": \"test\",\n" + "     \"fields\" : [\n"
            + "     {\"name\": \"a\", \"type\": \"string\" , \"java-class\": \"java.math.BigInteger\"},\n"
            + "     {\"name\": \"b\", \"type\": \"string\"}\n" + "     ]\n" + "     }";
    Schema schema = Schema.parse(stringSchema);
    GenericRecord gr = new GenericData.Record(schema);
    GenericRecord gr1 = new GenericData.Record(schema);

    private void createRecord() {
        String s = "100000000000000000000000000";
        BigInteger b = new BigInteger(s);
        gr.put("a", b);
        gr.put("b", "chandra");

        String s1 = "55555555555555555555555555555";
        BigInteger b1 = new BigInteger(s1);
        gr1.put("a", b1);
        gr1.put("b", "ashwini");
    }

    private void serialize() throws IOException {
        File file = new File("/tmp/users.avro");
        DatumWriter<GenericRecord> datumWriter = new ReflectDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(gr);
        dataFileWriter.append(gr1);
        dataFileWriter.close();
    }

    private void deserialize() throws IOException {
        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new ReflectDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(new File("/tmp/users.avro"),
            datumReader);
        GenericRecord user = null;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }
    }

    public static void main(String args[]) throws EventEncoderException, EventDecoderException, IOException {
        AvrOserializerAgain avroDataTypes1 = new AvrOserializerAgain();
        avroDataTypes1.createRecord();
        System.out.println(avroDataTypes1.gr.toString());
        avroDataTypes1.serialize();
        avroDataTypes1.deserialize();
        List<Schema.Field> fields = avroDataTypes1.schema.getFields();
        for (Schema.Field field : fields) {
            System.out.println(field.name() + field.getJsonProps().toString());
            Schema schema = field.schema();
            System.out.println(schema.toString());
            schema.addProp("java-classpath", "vdeghd");
            System.out.println(schema.toString());
            System.out.println(schema.getProps());

        }

    }

}
