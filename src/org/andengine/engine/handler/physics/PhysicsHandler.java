package org.andengine.engine.handler.physics;

import org.andengine.engine.handler.BaseEntityUpdateHandler;
import org.andengine.entity.IEntity;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:53:07 - 24.12.2010
 */
public class PhysicsHandler extends BaseEntityUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean mEnabled = true;

	protected float mAccelerationX = 0;
	protected float mAccelerationY = 0;

	protected float mVelocityX = 0;
	protected float mVelocityY = 0;

	protected float mAngularVelocity = 0;
	protected double degreeTotalRad = 0;
	// ===========================================================
	// Constructors
	// ===========================================================

	public PhysicsHandler(final IEntity pEntity) {
		super(pEntity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public boolean isEnabled() {
		return this.mEnabled;
	}

	public void setEnabled(final boolean pEnabled) {
		this.mEnabled = pEnabled;
	}

	public float getVelocityX() {
		return this.mVelocityX;
	}

	public float getVelocityY() {
		return this.mVelocityY;
	}

	public void setVelocityX(final float pVelocityX) {
		this.mVelocityX = pVelocityX;
	}

	public void setVelocityY(final float pVelocityY) {
		this.mVelocityY = pVelocityY;
	}

	public void setVelocity(final float pVelocity) {
		this.mVelocityX = pVelocity;
		this.mVelocityY = pVelocity;
	}

	public void setVelocity(final float pVelocityX, final float pVelocityY) {
		this.mVelocityX = pVelocityX;
		this.mVelocityY = pVelocityY;
	}

	public float getAccelerationX() {
		return this.mAccelerationX;
	}

	public float getAccelerationY() {
		return this.mAccelerationY;
	}

	public void setAccelerationX(final float pAccelerationX) {
		this.mAccelerationX = pAccelerationX;
	}

	public void setAccelerationY(final float pAccelerationY) {
		this.mAccelerationY = pAccelerationY;
	}

	public void setAcceleration(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX = pAccelerationX;
		this.mAccelerationY = pAccelerationY;
	}

	public void setAcceleration(final float pAcceleration) {
		this.mAccelerationX = pAcceleration;
		this.mAccelerationY = pAcceleration;
	}

	public void accelerate(final float pAccelerationX, final float pAccelerationY) {
		this.mAccelerationX += pAccelerationX;
		this.mAccelerationY += pAccelerationY;
	}

	public float getAngularVelocity() {
		return this.mAngularVelocity;
	}

	public void setAngularVelocity(final float pAngularVelocity) {
		this.mAngularVelocity = pAngularVelocity;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected void onUpdate(final float pSecondsElapsed, final IEntity pEntity) {
		if(this.mEnabled) {
			/* Apply linear acceleration. */
			final float accelerationX = this.mAccelerationX;
			final float accelerationY = this.mAccelerationY;
			float t = pSecondsElapsed;
			float r = 100;
			double degree = 36 * pSecondsElapsed;
			double degreeRad = degree * Math.PI/180;
			degreeTotalRad += degreeRad;
			
//			Log.d("ddw","accelerationX: "+accelerationX);
//			Log.d("ddw","accelerationY: "+accelerationY);
//			Log.d("ddw","pSecondsElapse: "+pSecondsElapsed);
//			if(accelerationX != 0 || accelerationY != 0) {
//				this.mVelocityX += accelerationX * pSecondsElapsed;
//				this.mVelocityY += accelerationY * pSecondsElapsed;
//			}
			
			
//			Log.d("ddw","mVelocityX: "+accelerationX);
//			Log.d("ddw","mVelocityY: "+accelerationY);
			
			/* Apply angular velocity. */
			final float angularVelocity = this.mAngularVelocity;
			if(angularVelocity != 0) {
				pEntity.setRotation(pEntity.getRotation() + angularVelocity * pSecondsElapsed);
			}

			/* Apply linear velocity. */
//			final float velocityX = this.mVelocityX;
//			final float velocityY = this.mVelocityY;
//			if(velocityX != 0 || velocityY != 0) {
//				pEntity.setPosition(pEntity.getX() + velocityX * pSecondsElapsed, pEntity.getY() + velocityY * pSecondsElapsed);
//			}
			double new_x = r * Math.cos(Math.PI - degreeTotalRad);
			double new_y = r - (r * Math.sin(Math.PI - degreeTotalRad));
			pEntity.setPosition((float)new_x+200, (float)new_y+200);
//			Log.d("ddw","original X: "+ pEntity.getX());
//			Log.d("ddw","original Y: "+ pEntity.getY());
//			Log.d("ddw","result X: "+ pEntity.getX() + velocityX * pSecondsElapsed);
//			Log.d("ddw","result Y: "+ pEntity.getY() + velocityY * pSecondsElapsed);
		}
	}

	@Override
	public void reset() {
		this.mAccelerationX = 0;
		this.mAccelerationY = 0;
		this.mVelocityX = 0;
		this.mVelocityY = 0;
		this.mAngularVelocity = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
