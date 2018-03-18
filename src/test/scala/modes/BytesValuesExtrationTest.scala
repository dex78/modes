package modes
import org.scalatest.FunSuite

class BytesValuesExtrationTest extends FunSuite {
  class BytesValueExtractionImpl(val bytes: List[Byte]) extends BytesValueExtraction


  test("One byte is extracted correctly") {
    val bytes = List[Byte](0x97.toByte)
    val instance = new BytesValueExtractionImpl(bytes)
    assert(instance.extractValue(0, 7) == 0x97)
  }

  test("Last Seven Bits in one byte are extracted correctly") {
    val bytes = List[Byte](0x97.toByte)
    val instance = new BytesValueExtractionImpl(bytes)
    assert(instance.extractValue(1, 7) == 0x17)
  }

  test("First Seven Bits in one byte are extracted correctly") {
    val bytes = List[Byte](0x97.toByte)
    val instance = new BytesValueExtractionImpl(bytes)
    assert(instance.extractValue(0, 6) == 0x4b)
  }

  test("Extraction with 3 Bytes") {
    val bytes = List[Byte](0x97.toByte, 0xF7.toByte, 0x7F.toByte)
    val instance = new BytesValueExtractionImpl(bytes)
    assert(instance.extractValue(0, 23) == 0x97F77F)
  }

  test("Extract something within 3 Bytes") {
    val bytes = List[Byte](0x97.toByte, 0xF7.toByte, 0x7F.toByte)
    val instance = new BytesValueExtractionImpl(bytes)
    assert(instance.extractValue(4, 19) == 0x7F77)
  }
}
