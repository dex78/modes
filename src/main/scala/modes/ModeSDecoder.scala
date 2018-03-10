package modes

class ModeSDecoder {
  def sayHello(name: String) = s"Hello $name"

  def decode(bytes: ModeSRawBytes): DownlinkFormat = null
}
