//package com.trippntechnology.tnt.flashcards.views
//
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Paint
//import android.util.AttributeSet
//import androidx.core.content.ContextCompat
//import com.trippntechnology.tnt.flashcards.R
//import com.trippntechnology.tnt.flashcards.util.view.BaseView
//
//class NoteSelectionView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
//    BaseView(context, attrs, defStyleAttr) {
//    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
//    constructor(context: Context) : this(context, null)
//
//
//    private val CLEF_SEPARATOR_SPACE = LINE_SPACING * 6f
//    private val STAFF_PADDING = dpToPx(8f)
//
//    //NUMBER OF HALF STEPS BELOW THE MIDDLE LINE ON THE STAFF
//    private val LOWEST_BASS_NOTE_OFFSET = 8
//    private val LOWEST_HIGH_NOTE_OFFSET = 8
//
//    private val NUMBER_BASS_NOTES: Float = 17f
//    private val NUMBER_HIGH_NOTES: Float = 17f
//
//
//    private val points = FloatArray(40)
//
//    private val selectedPaint = Paint().apply {
//        color = ContextCompat.getColor(context, R.color.selectedStroke)
//        setShadowLayer(
//            dpToPx(8f),
//            0f,
//            dpToPx(4f),
//            ContextCompat.getColor(context, R.color.selectedStrokeShadow)
//        )
//        isAntiAlias = true
//        strokeWidth = STROKE_SIZE
//    }
//
//    override fun onDraw(canvas: Canvas?) {
//        canvas ?: return
//
//        val centerWidth = width / 2f
//        val centerHeight = height / 2f
//        val startX = dpToPx(8f)
//        val endX = width - dpToPx(8f)
//
//        val upperStaffMid =
//            centerHeight - CLEF_SEPARATOR_SPACE / 2 - LINE_SPACING * 2
//        val bottomStaffMid =
//            centerHeight + CLEF_SEPARATOR_SPACE / 2 + LINE_SPACING * 2
//
//        //Create staff lines and draw
//        var yPos = upperStaffMid - LINE_SPACING * 2
//        var pass = 0
//        for (i in 0 until points.size) {
//            if (i == 20) {
//                yPos = bottomStaffMid - LINE_SPACING * 2
//            }
//            if (i % 4 == 0 || i == 0) {
//                points[i] = startX
//            } else if (i % 2 == 0) {
//                points[i] = endX
//            } else if (i % 2 != 0) {
//                points[i] = yPos
//                pass++
//                if (pass == 2) {
//                    pass = 0
//                    yPos += LINE_SPACING
//                }
//            }
//        }
//        canvas.drawLines(points, blackPaint)
//        canvas.save()
//
//        //Draw clefs
//        canvas.translate(0f, upperStaffMid - TREBLE_HEIGHT / 2)
//        trebleDrawable?.draw(canvas)
//        canvas.restore()
//        canvas.save()
//        canvas.translate(
//            dpToPx(8f),
//            bottomStaffMid - BASS_HEIGHT / 2 - LINE_SPACING / 2 + STROKE_SIZE * 4
//        )
//        bassDrawable?.draw(canvas)
//        canvas.restore()
//
//        canvas.save()
//        //Lower staff low note starting position
//        canvas.translate(CLEF_WIDTH.toFloat(), bottomStaffMid + HALF_STEP_UP)
//        canvas.drawLine(0f, 0f, width.toFloat(), 0f, selectedPaint)
//        canvas.translate(0f, HALF_STEP_DOWN * (LOWEST_BASS_NOTE_OFFSET))
//        drawNote(canvas, NUMBER_BASS_NOTES.toInt(), LOWEST_BASS_NOTE_OFFSET)
//        canvas.restore()
//
//
////        canvas.save()
////        //Lower staff low note starting position
////        canvas.translate(CLEF_WIDTH.toFloat(), upperStaffMid)
////        canvas.translate(0f, HALF_STEP_DOWN * LOWEST_HIGH_NOTE_OFFSET)
////        drawNote(canvas, NUMBER_HIGH_NOTES.toInt(), LOWEST_HIGH_NOTE_OFFSET)
////        canvas.restore()
//
//    }
//
//    @Suppress("SameParameterValue")
//    private fun drawNote(canvas: Canvas, numberOfNotes: Int, noteOffset: Int) {
//        val padding: Float = (width - CLEF_WIDTH) / 8f
//        for (i in 0 until numberOfNotes) {
//            val priorRotate = canvas.save()
//            canvas.rotate(NOTE_BASE_ROTATION_ANGLE, NOTE_BASE_WIDTH / 2, NOTE_BASE_HEIGHT / 2)
//            canvas.drawOval(noteBase, blackPaint)
//            canvas.restoreToCount(priorRotate)
//            if (i < noteOffset - 5 || i > noteOffset + 5) {
//                canvas.save()
//                if (noteOffset > 5) {
//                    var currentOffset = noteOffset + i
//                    if (currentOffset % 2 != 0) {
//                        var yPos = NOTE_BASE_HEIGHT
//                        while (currentOffset > 5) {
//                            canvas.drawLine(
//                                0f,
//                                0f,
//                                NOTE_BASE_WIDTH,
//                                0f,
//                                blackPaint
//                            )
//                            yPos+=FULL_STEP_UP
//                            currentOffset--
//                            canvas.translate(0f,yPos)
//                        }
//                    } else {
//                        while (currentOffset > 5) {
//                            var yPos = NOTE_BASE_HEIGHT/2
//                            canvas.drawLine(
//                                0f,
//                                0f,
//                                NOTE_BASE_WIDTH,
//                                0f,
//                                blackPaint
//                            )
//                            yPos+=FULL_STEP_UP
//                            currentOffset--
//                            canvas.translate(0f,yPos)
//                        }
//                    }
//                } else if (i > noteOffset + 6) {
//                    var currentOffset = noteOffset + 6 + i
//                    if (currentOffset % 2 != 0) {
//                        var yPos = 0f
//                        while (currentOffset > 5) {
//                            canvas.drawLine(
//                                0f,
//                                yPos,
//                                NOTE_BASE_WIDTH,
//                                yPos,
//                                blackPaint
//                            )
//                            yPos+=FULL_STEP_DOWN
//                            currentOffset--
//                        }
//                    } else {
//                        while (currentOffset > 5) {
//                            var yPos = NOTE_BASE_HEIGHT/2
//                            canvas.drawLine(
//                                0f,
//                                yPos,
//                                NOTE_BASE_WIDTH,
//                                yPos,
//                                blackPaint
//                            )
//                            yPos+=FULL_STEP_DOWN
//                            currentOffset--
//                        }
//                    }
//                }
//                canvas.restore()
//            }
//            if ((i + 1) % 8 == 0) {
//                canvas.translate(-1 * padding * 7, HALF_STEP_UP)
//            } else {
//                canvas.translate(padding, HALF_STEP_UP)
//            }
//        }
//    }
//}