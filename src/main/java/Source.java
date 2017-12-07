import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import lu.sandstone.d2.storage.SomeType;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

public class Source implements SourceFunction<SomeType> {

  public void run(SourceContext sourceContext) throws Exception {

    while (true) {
      String name = "xyz";

      Map<CharSequence, ByteBuffer> map = new HashMap<>();
      map.put("theValue", ByteBuffer.wrap(new String("hello").getBytes()));

      sourceContext.collect(SomeType.newBuilder().setName(name)
          .setTheMap(map).build());

      Thread.sleep(1000);
    }
  }

  public void cancel() {
  }
}
