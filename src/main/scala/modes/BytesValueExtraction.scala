package modes


trait BytesValueExtraction {
  val bitsInAByte = 8
  val bytes: List[Byte]

  def extractValue(startBit: Byte, endBitInclusive: Byte): Int = {
    val startByte = startBit / bitsInAByte
    val endByte = endBitInclusive / bitsInAByte
    val startBitInByte = startBit % bitsInAByte
    val endBitInByte = endBitInclusive % bitsInAByte

    var extract: Int = ((bytes(startByte) << startBitInByte) & 0xFF) >>> startBitInByte
    for (currentByte <- (startByte + 1) to endByte) {
      extract <<= bitsInAByte
      extract |= bytes(currentByte) & 0xFF
    }
    extract >>> (bitsInAByte - endBitInByte - 1)
  }
}
