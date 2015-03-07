class CreateEntries < ActiveRecord::Migration
  def change
    create_table :entries do |t|
      t.integer :value
    end
  end
end
