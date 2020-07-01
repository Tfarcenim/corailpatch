package tfar.corailpatch.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ovh.corail.tombstone.compatibility.SupportMods;


@Mixin(SupportMods.class)
abstract class SupportModMixin {

	@Inject(method = "<clinit>",at = @At(value = "INVOKE",target = "java/lang/Thread.currentThread()Ljava/lang/Thread;"),cancellable = true)
	private static void stop(CallbackInfo ci){
		//this is crash code, lets prevent that
		ci.cancel();
	}
}
