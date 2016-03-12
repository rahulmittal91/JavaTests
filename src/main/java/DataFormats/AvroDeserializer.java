package DataFormats;

import com.intuit.blink.replicator.common.exceptions.EventDecoderException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by rmittal1 on 3/1/16.
 */
public class AvroDeserializer {
    private static Logger logger = Logger.getLogger(AvroDeserializer.class);
    private Schema avroSchema;

    public AvroDeserializer(Schema schema) {
        this.avroSchema = schema;
    }

    public GenericRecord fromBytes(byte[] eventBytes) throws EventDecoderException {
        GenericRecord record = null;

        try {
            GenericDatumReader e = new GenericDatumReader(this.avroSchema);
            BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(eventBytes, 4 , eventBytes.length -4, (BinaryDecoder)null);
            record = (GenericRecord)e.read((Object)null, decoder);
            return record;
        } catch (IOException var5) {
            logger.error("Error while decoding avro message", var5);
            throw new EventDecoderException(var5.getMessage(), var5);
        }
    }
}
