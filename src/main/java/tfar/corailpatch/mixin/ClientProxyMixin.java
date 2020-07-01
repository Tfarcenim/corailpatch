package tfar.corailpatch.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ovh.corail.tombstone.proxy.ClientProxy;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(value = ClientProxy.class,remap = false)
public class ClientProxyMixin {
	@Redirect(method = "init",at = @At(value = "INVOKE",target = "java/util/stream/Stream.anyMatch(Ljava/util/function/Predicate;)Z"))
	private boolean stop(Stream stream, Predicate<? super Object> predicate){
		//what the fuck dude
		return false;
	}

	@Redirect(method = "init",at = @At(value = "INVOKE",target = "ovh/corail/tombstone/helper/Helper.existClass(Ljava/lang/String;)Z"))
	private boolean seriously(String className){
		//just stop
		return false;
	}
}
