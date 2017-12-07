import java.nio.ByteBuffer;
import java.util.Map;

import lu.sandstone.d2.storage.SomeType;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class Main {

  public static void main(String[] args) throws Exception {
    final StreamExecutionEnvironment env =
        StreamExecutionEnvironment.getExecutionEnvironment();

    DataStream<SomeType> stream = env.addSource(new Source());

    stream.map(new MapFunction<SomeType, String>() {
      @Override
      public String map(SomeType type) throws Exception {
        Map<CharSequence, ByteBuffer> map = type.getTheMap();

        return new String(map.get("theValue").array());
      }
    }).print();

    env.execute();
  }
}
