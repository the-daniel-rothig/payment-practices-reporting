/*
 * Copyright (C) 2017  Department for Business, Energy and Industrial Strategy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package questionnaire

case class Choice(label: String, value: String)

sealed trait Question {
  def choices: Seq[Choice]

  def inline: Boolean

  def text: String

  def hintText: Option[String]
}

case class YesNoQuestion(text: String, hintText: Option[String]) extends Question {
  override def choices: Seq[Choice] = Seq(Choice("Yes", YesNo.Yes.entryName), Choice("No", YesNo.No.entryName))

  override def inline = true
}

case class MultipleChoiceQuestion(text: String, hintText: Option[String], choices: Seq[Choice]) extends Question {
  override def inline = false
}

case class ThresholdQuestions(
                               turnoverQuestion: YesNoQuestion,
                               balanceSheetQuestion: YesNoQuestion,
                               employeesQuestion: YesNoQuestion
                             )