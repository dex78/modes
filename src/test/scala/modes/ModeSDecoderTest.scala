package modes

import org.scalatest.FunSuite

class ModeSDecoderTest extends FunSuite {

  test("Decoder method works correctly") {
    val decoder = new ModeSDecoder
    assert(decoder.sayHello("Mode S Decoder") == "Hello Mode S Decoder")
  }
}
